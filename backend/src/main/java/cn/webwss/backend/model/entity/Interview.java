package cn.webwss.backend.model.entity;

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
     * 面试id
     */
    @TableId(type = IdType.AUTO)
    private Integer interviewId;

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

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}