package cn.webwss.backend.job;

import cn.hutool.json.JSONObject;
import cn.webwss.backend.model.entity.Staff;
import cn.webwss.backend.model.vo.data.SendAdminVO;
import cn.webwss.backend.model.vo.data.SendStaffVO;
import cn.webwss.backend.service.StaffService;
import cn.webwss.backend.utils.WechatUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class DayJob {

    @Resource
    private StaffService staffService;

    @Resource
    private WechatUtil wechatUtil;

    private final String ToUrl = "https://test.com";

    /**
     * 每日7点30执行
     * 发送每个HR每次新增人才数
     */
    @Scheduled(cron = "0 30 7 * * ?")
    public void sendAdminData() throws Exception {
//        获取accessToken
        String accessToken = wechatUtil.getAccessToken();
//        获取管理员wx_user_id
        List<Staff> adminList = staffService.getAdminList();
        System.out.println("adminList" + adminList);
//        构造请求地址
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;
        List<SendAdminVO> sendAdminVOS = staffService.sendAdminData();
        StringBuilder sendMessage = new StringBuilder();
        sendAdminVOS.forEach(sendAdminVO -> {
            String name = sendAdminVO.getName();
            String count = sendAdminVO.getCount().toString();
            String message = "<div class=\"highlight\">员工姓名:"+ name + "，新增人才数：" + count + "</div>";
            sendMessage.append(message);
        });
//        构造请求参数
        JSONObject jsonObject = new JSONObject();
        adminList.forEach(admin -> {
            jsonObject.put("touser", admin.getWxUserId());
            jsonObject.put("toparty", "@all");
            jsonObject.put("totag", "@all");
            jsonObject.put("msgtype", "textcard");
            jsonObject.put("agentid", "1000009");
            JSONObject textcard = new JSONObject();
            textcard.put("title","昨日HR新增人才数");
            textcard.put("description", sendMessage);
            textcard.put("url", ToUrl);
            jsonObject.put("textcard", textcard);
            jsonObject.put("safe", "1");
            jsonObject.put("enable_id_trans", "0");
            jsonObject.put("enable_duplicate_check", "0");
            jsonObject.put("duplicate_check_interval", "1800");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
            httpPost.setEntity(stringEntity);
//        发送
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String responseString = null;
            try {
                responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 打印响应内容
            System.out.println("Response: " + responseString);
        });


    }

    /**
     * 每日8点/13点执行一次
     * 发送该员工当天面试通知
     */
    @Scheduled(cron = "0 0 8,13 * * ?")
    public void sendStaffData() throws Exception {
        //        获取accessToken
        String accessToken = wechatUtil.getAccessToken();
//        构造请求地址
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;
        List<SendStaffVO> sendStaffVOS = staffService.sendStaffData();

//        获取员工列表
        List<Staff> staffList = staffService.list();
        staffList.forEach(staff -> {
            StringBuilder sendMessage = new StringBuilder();
            AtomicReference<String> wxUserId = new AtomicReference<>();
            sendStaffVOS.forEach(sendStaffVO -> {
                if (staff.getStaffId().equals(sendStaffVO.getCreateStaffId())) {
                    wxUserId.set(staff.getWxUserId());
                    sendMessage.append("<div class=\"highlight\">面试人：").append(sendStaffVO.getName()).append("，面试时间：").append(sendStaffVO.getNextInterviewTime()).append("</div>");
                }
            });
            if (wxUserId.get() != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("touser", wxUserId);
                jsonObject.put("toparty", "@all");
                jsonObject.put("totag", "@all");
                jsonObject.put("msgtype", "textcard");
                jsonObject.put("agentid", "1000009");
                JSONObject textcard = new JSONObject();
                textcard.put("title","今日面试提醒");
                textcard.put("description", sendMessage);
                textcard.put("url", ToUrl);
                jsonObject.put("textcard", textcard);
                jsonObject.put("safe", "1");
                jsonObject.put("enable_id_trans", "0");
                jsonObject.put("enable_duplicate_check", "0");
                jsonObject.put("duplicate_check_interval", "1800");
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Content-Type", "application/json");
                StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
                httpPost.setEntity(stringEntity);
//        发送
                CloseableHttpResponse response = null;
                try {
                    response = httpClient.execute(httpPost);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String responseString = null;
                try {
                    responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // 打印响应内容
                System.out.println("Response: " + responseString);
            }

        });



    }



}
