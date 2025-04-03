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
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色（admin、hr）
     */
    private String role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态（1-正常、2-冻结）
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}