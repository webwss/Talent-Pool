package cn.webwss.backend.model.vo.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxAccessTokenVO {

    @JsonProperty("errcode")
    private String errcode;

    @JsonProperty("errmsg")
    private String errmsg;

    @JsonProperty("access_token")
    private String access_token;

    @JsonProperty("expires_in")
    private Integer expires_in;

}
