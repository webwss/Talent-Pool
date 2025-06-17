package cn.webwss.backend.model.dto.interact;

import lombok.Data;

@Data
public class InteractAddDTO {

    /**
     * 人才id
     */
    private Integer talentId;

    /**
     *  沟通人
     */
    private String interactStaff;

    /**
     * 沟通时间
     */
    private String interactTime;

    /**
     * 沟通内容
     */
    private String interactContent;

}
