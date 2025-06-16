package cn.webwss.backend.utils;

import cn.webwss.backend.config.CacheManager;
import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.model.vo.login.WxAccessTokenVO;
import cn.webwss.backend.model.vo.login.WxUserInfoVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class WechatUtil {


    /**
     * 获取微信的access_token
     * @param corpId
     * @param corpSecret
     * @return
     * @throws Exception
     */

    @Value("${wx.corpId}")
    private String corpId;

    @Value("${wx.corpSecret}")
    private String corpSecret;



    public String getAccessToken() throws Exception {
        System.out.println("开始获取accessToken，corpId" + corpId);
//        查询本地缓存是否存在accessToken
        CacheManager cacheManager = new CacheManager();
        String accessToken = cacheManager.getAccessToken();
        if (accessToken != null) {
            System.out.println("本地存在accessToken");
            return accessToken;
        }
//        重新获取
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpId + "&corpsecret=" + corpSecret;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
//            jackson解析json
            ObjectMapper objectMapper = new ObjectMapper();
            WxAccessTokenVO wxAccessTokenVO = objectMapper.readValue(result, WxAccessTokenVO.class);
            System.out.println("wxAccessTokenVO:" + wxAccessTokenVO);
            System.out.println("accessToken:" + wxAccessTokenVO.getAccess_token());
//            将accessToken缓存到本地
            cacheManager.setAccessToken(wxAccessTokenVO.getAccess_token());
            System.out.println("重新获取accessToken");
            return wxAccessTokenVO.getAccess_token();
        }
        return null;
    }

    /**
     * 消费code换取用户信息
     */
    public WxUserInfoVO getUserInfo(String code) throws Exception {
//        获取access_token
        String accessToken = getAccessToken();
//        构造请求信息
        String url = "https://qyapi.weixin.qq.com/cgi-bin/auth/getuserinfo?access_token=" + accessToken + "&code=" + code;
//        发送请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
//      jackson解析json
        ObjectMapper objectMapper = new ObjectMapper();
        WxUserInfoVO wxUserInfoVO = objectMapper.readValue(result, WxUserInfoVO.class);
        System.out.println("wxUserInfoVO:" + wxUserInfoVO);
        if (wxUserInfoVO.getUserid() == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "获取用户信息失败");
        }
//      获取用户姓名
        String url1 = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&userid=" + wxUserInfoVO.getUserid();
        HttpGet httpGet1 = new HttpGet(url1);
        HttpResponse response1 = httpClient.execute(httpGet1);
        HttpEntity entity1 = response1.getEntity();
        String result1 = EntityUtils.toString(entity1, "UTF-8");
        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode jsonNode = objectMapper1.readTree(result1);
        // 增加 null 判断
        JsonNode nameNode1 = jsonNode.get("name");
        String name = nameNode1 != null ? nameNode1.asText() : ""; // 或者设置默认值、抛异常等处理方式
        wxUserInfoVO.setName(name);
        return wxUserInfoVO;
    }


}