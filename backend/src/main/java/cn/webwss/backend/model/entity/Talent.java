package cn.webwss.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName talent
 */
@TableName(value ="talent")
@Data
public class Talent implements Serializable {
    /**
     * 人才id
     */
    @TableId(type = IdType.AUTO)
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}