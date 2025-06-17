package cn.webwss.backend.model.dto.talent;

import lombok.Data;

import java.util.Date;

@Data
public class TalentEditBaseInfoDTO {

    /**
     * 人才id
     */
    private Integer talentId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 岗位id
     */
    private Integer postId;

    /**
     * 学历id
     */
    private Integer educationId;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 性别（1-男，2-女）
     */
    private Integer sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 婚育情况（1-已婚已育，2-已婚未育，3-未婚未育）
     */
    private Integer marriage;


    /**
     * 目前薪资
     */
    private String currentSalary;

    /**
     * 期望薪资
     */
    private String expectedSalary;

    /**
     * 工作状态（1-在职，2-离职）
     */
    private Integer jobStatus;

    /**
     * 工作年限
     */
    private Integer jobYear;

    /**
     * 地址
     */
    private String address;

    /**
     * 目前岗位
     */
    private String currentPost;

    /**
     * 离职原因
     */
    private String reasonForLeaving;

    /**
     * 优势
     */
    private String advantage;

    /**
     * 劣势
     */
    private String disadvantage;

    /**
     * 下次面试时间
     */
    private Date nextInterviewTime;

    /**
     * 面试结果
     */
    private Integer result;






}
