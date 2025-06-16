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
 * @TableName staff
 */
@TableName(value ="staff")
@Data
public class Staff implements Serializable {
    /**
     * 员工id
     */
    @TableId(type = IdType.AUTO)
    private Integer staffId;

    /**
     * 企业微信用户id
     */
    private String wxUserId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     *  角色
     */
    private String role;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}