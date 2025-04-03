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
 * @TableName interview
 */
@TableName(value ="interview")
@Data
public class Interview implements Serializable {
    /**
     * 面试记录id
     */
    @TableId(type = IdType.AUTO)
    private Integer interviewId;

    /**
     * 人才id
     */
    private Integer talentId;

    /**
     * 约面员工
     */
    private String reservationStaff;

    /**
     * 面试官（多人）
     */
    private String interviewer;

    /**
     * 面试时间
     */
    private String interviewTime;

    /**
     * 面试评估
     */
    private String interviewAssess;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}