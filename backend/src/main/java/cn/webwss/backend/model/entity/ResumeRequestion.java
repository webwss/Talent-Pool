package cn.webwss.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName resume_requestion
 */
@TableName(value ="resume_requestion")
@Data
public class ResumeRequestion implements Serializable {
    /**
     * 简历问题id
     */
    @TableId(type = IdType.AUTO)
    private Integer resumeRequestionId;

    /**
     * 简历id
     */
    private Integer resumeId;

    /**
     * 简历问题
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}