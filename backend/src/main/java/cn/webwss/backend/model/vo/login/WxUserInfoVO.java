package cn.webwss.backend.model.vo.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxUserInfoVO {

    /**
     * 返回码
     */
    @JsonProperty("errcode")
    private String errcode;

    /**
     * 返回信息
     */
    @JsonProperty("errmsg")
    private  String errmsg;

    /**
     * 用户id
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 用户姓名
     */
    @JsonProperty("name")
    private String name;

}
