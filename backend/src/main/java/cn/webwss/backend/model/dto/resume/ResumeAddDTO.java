package cn.webwss.backend.model.dto.resume;

import lombok.Data;

@Data
public class ResumeAddDTO {

    /**
     * 人才id
     */
    private Integer talentId;

    /**
     * 简历图片
     */
    private String resumeUrl;

}
