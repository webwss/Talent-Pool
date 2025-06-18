package cn.webwss.backend.model.dto.staff;

import lombok.Data;

@Data
public class StaffEditDTO {

    /**
     * 员工id
     */
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
     * 角色 fk,hr,admin
     */
    private String role;


}
