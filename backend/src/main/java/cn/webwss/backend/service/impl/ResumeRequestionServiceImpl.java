package cn.webwss.backend.service.impl;

import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.mapper.ResumeMapper;
import cn.webwss.backend.mapper.ResumeRequestionMapper;
import cn.webwss.backend.model.entity.Resume;
import cn.webwss.backend.model.entity.ResumeRequestion;
import cn.webwss.backend.service.ResumeRequestionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

import static cn.webwss.backend.constant.StaffConstant.GENERATE_QUESTIONS_STATE;


/**
 * @author admin
 * @description 针对表【resume_requestion】的数据库操作Service实现
 * @createDate 2025-06-10 16:51:23
 */
@Service
public class ResumeRequestionServiceImpl extends ServiceImpl<ResumeRequestionMapper, ResumeRequestion>
        implements ResumeRequestionService {

    @Resource
    private ResumeMapper resumeMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private Executor generateQuestionsTaskExecutor;

    @Value("${ai.openai.api-key}")
    private String apiKey;

    /**
     * 生成面试题
     *
     * @param resumeId 简历ID
     * @return
     */
    @Override
    public Boolean generateQuestions(Integer resumeId) {
//        检测是否正在生成
        if (stringRedisTemplate.opsForValue().get(GENERATE_QUESTIONS_STATE + resumeId) != null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "正在生成面试题");
        }

//        检测是否已有面试题
        ResumeRequestion resumeRequestion = getOne(new QueryWrapper<ResumeRequestion>().eq("resume_id", resumeId));
        if (resumeRequestion != null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "该简历已有面试题");
        }

        generateQuestionsTaskExecutor.execute(() -> {
            System.out.println("执行生成面试题异步任务，当前线程：" + Thread.currentThread().getName());
            //         设置生成面试题状态
            stringRedisTemplate.opsForValue().set(GENERATE_QUESTIONS_STATE + resumeId, "1");

            // 请求的URL
            String url = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";
//        根据resumeId获取图片地址
            Resume resume = resumeMapper.selectById(resumeId);
            if (resume == null || resume.getResumeUrl() == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
            }
            String resumeUrl = resume.getResumeUrl();
            // 创建HttpClient对象
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // 创建HttpPost对象
                HttpPost httpPost = new HttpPost(url);

                // 设置请求头
                httpPost.setHeader("Host", "dashscope.aliyuncs.com");
                httpPost.setHeader("Authorization", apiKey);
                httpPost.setHeader("Content-Type", "application/json");

                // 使用Jackson构建请求体
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectNode rootNode = objectMapper.createObjectNode();

                // 添加model字段
                rootNode.put("model", "qwen-vl-max-latest");

                // 创建messages数组
                ArrayNode messagesArray = objectMapper.createArrayNode();

                // 创建system消息
                ObjectNode systemMessage = objectMapper.createObjectNode();
                systemMessage.put("role", "system");

                ArrayNode systemContentArray = objectMapper.createArrayNode();
                ObjectNode systemContent = objectMapper.createObjectNode();
                systemContent.put("type", "text");
                systemContent.put("text", "You are a helpful assistant.");
                systemContentArray.add(systemContent);

                systemMessage.set("content", systemContentArray);
                messagesArray.add(systemMessage);

                // 创建user消息
                ObjectNode userMessage = objectMapper.createObjectNode();
                userMessage.put("role", "user");

                ArrayNode userContentArray = objectMapper.createArrayNode();

                // 添加图片URL内容
                ObjectNode imageUrlContent = objectMapper.createObjectNode();
                imageUrlContent.put("type", "image_url");
                ObjectNode imageUrlDetails = objectMapper.createObjectNode();
                imageUrlDetails.put("url", resumeUrl);
                imageUrlContent.set("image_url", imageUrlDetails);
                userContentArray.add(imageUrlContent);

                // 添加文本内容
                ObjectNode userTextContent = objectMapper.createObjectNode();
                userTextContent.put("type", "text");
                userTextContent.put("text", "请根据这张图片生成相关的面试题,输出格式为HTML格式,不要出现```html,只需要body标签以内的内容，每条面试题需要加粗、换行、标记上第几题");
                userContentArray.add(userTextContent);

                userMessage.set("content", userContentArray);
                messagesArray.add(userMessage);

                // 将messages数组添加到根节点
                rootNode.set("messages", messagesArray);

                // 将JSON对象序列化为字符串
                String requestBody = objectMapper.writeValueAsString(rootNode);

                // 设置请求体
                StringEntity requestEntity = new StringEntity(requestBody, "UTF-8");
                httpPost.setEntity(requestEntity);

                // 发送请求并获取响应
                org.apache.http.HttpResponse response = httpClient.execute(httpPost);
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");

                // 打印响应结果
                System.out.println(result);
                // 解析 JSON 响应
                JsonNode rootNode1 = objectMapper.readTree(result);
                JsonNode choicesNode = rootNode1.get("choices");

                ResumeRequestion resumeRequestion1 = new ResumeRequestion();

                if (choicesNode != null && choicesNode.isArray() && !choicesNode.isEmpty()) {
                    JsonNode messageNode = choicesNode.get(0).get("message");
                    if (messageNode != null) {
                        String content = messageNode.get("content").asText();
                        resumeRequestion1.setResumeId(resumeId);
                        resumeRequestion1.setContent(content);
                        boolean save = save(resumeRequestion1);
                        //                    移除生成面试题的状态
                        stringRedisTemplate.delete(GENERATE_QUESTIONS_STATE + resumeId);
                        if (!save) {
                            throw new BusinessException(ErrorCode.OPERATION_ERROR, "面试题添加失败");
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    /**
     * 获取面试题
     * @param resumeId
     * @return
     */
    @Override
    public ResumeRequestion getQuestions(Integer resumeId) {
        if (resumeId == null || resumeId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
//        查询面试题是否生成中
        String generateQuestionsState = stringRedisTemplate.opsForValue().get(GENERATE_QUESTIONS_STATE + resumeId);
        if (generateQuestionsState != null) {
            ResumeRequestion resumeRequestion = new ResumeRequestion();
            resumeRequestion.setContent("1");
        }
        QueryWrapper<ResumeRequestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resume_id", resumeId);
        return getOne(queryWrapper);
    }
}
