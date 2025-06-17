package cn.webwss.backend.model.vo.data;

import lombok.Data;

import java.util.Date;

@Data
public class SendStaffVO {

    private Integer talentId; // 人才id

    private String name; // 姓名

    private Date nextInterviewTime; // 下次面试时间

    private Integer createStaffId; // 创建人id


}
