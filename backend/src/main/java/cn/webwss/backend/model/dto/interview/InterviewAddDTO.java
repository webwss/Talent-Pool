package cn.webwss.backend.model.dto.interview;

import lombok.Data;

@Data
public class InterviewAddDTO {

    /**
     * 人才id
     */
    private Integer talentId;

    /**
     * 面试官
     */
    private String interviewer;

    /**
     * 面试时间
     */
    private String interviewTime;

    /**
     * 面试评估
     */
    private String interviewAccess;


}
