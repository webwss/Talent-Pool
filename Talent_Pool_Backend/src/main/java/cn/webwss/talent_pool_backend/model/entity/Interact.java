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
 * @TableName interact
 */
@TableName(value ="interact")
@Data
public class Interact implements Serializable {
    /**
     * 沟通记录id
     */
    @TableId(type = IdType.AUTO)
    private Integer interactId;

    /**
     * 人才id
     */
    private Integer talentId;

    /**
     * 沟通人
     */
    private String interactStaff;

    /**
     * 沟通日期
     */
    private String interactTime;

    /**
     * 沟通内容
     */
    private String interactContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createStaffId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}