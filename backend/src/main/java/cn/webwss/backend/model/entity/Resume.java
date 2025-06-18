package cn.webwss.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName resume
 */
@TableName(value ="resume")
@Data
public class Resume implements Serializable {
    /**
     * 简历id
     */
    @TableId(type = IdType.AUTO)
    private Integer resumeId;

    /**
     * 人才id
     */
    private Integer talentId;

    /**
     * 简历图片
     */
    private String resumeUrl;

    /**
     * 相关面试题
     */
    @TableField(exist = false)
    private ResumeRequestion resumeRequestion;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}