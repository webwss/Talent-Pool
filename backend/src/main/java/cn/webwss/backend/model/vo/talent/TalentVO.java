package cn.webwss.backend.model.vo.talent;

import cn.webwss.backend.model.entity.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TalentVO {

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
     * 个人照片
     */
    private String photo;

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
     * 面试结果（1-待定，2-通过，3-未通过）
     */
    private Integer result;

    /**
     * 下次面试时间
     */
    private Date nextInterviewTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 编辑次数
     */
    private Integer editSum;

    /**
     * 删除审核（1-默认，2-提交审核）
     */
    private Integer delStatus;

    /**
     * 删除原因
     */
    private String delReason;

    /**
     * 创建员工
     */
    private Integer createStaffId;


//    岗位信息
    private Post post;

//    学历信息
    private Education education;

//    创建员工信息
    private Staff staff;

//    沟通记录列表
    private List<Interact> interactList;

//    面试记录列表
    private List<Interview> interviewList;

//    简历列表
    private List<Resume> resumeList;



}
