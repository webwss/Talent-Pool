package cn.webwss.talent_pool_backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
     * 面试岗位
     */
    private String post;

    /**
     * 学历
     */
    private String education;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 性别（1-男、2-女）
     */
    private Integer sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 婚育情况（1-已婚已育、2-已婚未育、3-未婚未育）
     */
    private String marriage;

    /**
     * 个人照片
     */
    private String photo;

    /**
     * 目前薪酬
     */
    private String currentSalary;

    /**
     * 期望薪酬
     */
    private String expectedSalary;

    /**
     * 工作状态（1-在职、2-离职）
     */
    private Integer jobStatus;

    /**
     * 目前岗位
     */
    private String currentPosition;

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
     * 面试结果（1-待定、2-通过，3-未通过）
     */
    private Integer result;

    /**
     * 结果说明
     */
    private String resultDesc;

    /**
     * 下次面试时间
     */
    private String nextInterviewTime;

    /**
     * 创建员工id
     */
    private Integer createStaffId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除状态（1-未删除、2-待审核）
     */
    private Integer deleteStatus;

    /**
     * 删除原因
     */
    private String deleteReason;

    /**
     * 修改次数
     */
    private Integer updateSum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}