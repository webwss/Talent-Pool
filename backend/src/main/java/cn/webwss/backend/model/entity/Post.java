package cn.webwss.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    /**
     * 岗位id
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 岗位名称
     */
    private String postName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}