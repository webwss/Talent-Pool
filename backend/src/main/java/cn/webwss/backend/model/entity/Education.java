package cn.webwss.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName education
 */
@TableName(value ="education")
@Data
public class Education implements Serializable {
    /**
     * 学历id
     */
    @TableId(type = IdType.AUTO)
    private Integer educationId;

    /**
     * 学历名称
     */
    private String educationName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}