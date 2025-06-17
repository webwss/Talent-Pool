package cn.webwss.backend.model.dto.interact;

import lombok.Data;

@Data
public class InteractEditDTO {


    /**
     * 沟通id
     */
    private Integer interactId;


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
