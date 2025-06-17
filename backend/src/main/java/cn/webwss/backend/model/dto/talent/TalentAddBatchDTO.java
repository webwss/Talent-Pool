package cn.webwss.backend.model.dto.talent;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TalentAddBatchDTO {

    /**
     * 序号
     */
    @ExcelProperty("序号")
    private Integer seq;

    /**
     * 岗位
     */
    @ExcelProperty("岗位")
    private Integer postId;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    private String phone;

    /**
     * 性别
     */
    @ExcelProperty("性别")
    private Integer sex;

    /**
     * 出生年月
     */
    @ExcelProperty("出生年月")
    private String age;

    /**
     * 婚育情况
     */
    @ExcelProperty("婚育情况")
    private Integer marriage;

    /**
     * 学历
     */
    @ExcelProperty("学历")
    private Integer educationId;

    /**
     * 目前薪资
     */
    @ExcelProperty("目前薪资")
    private String currentSalary;

    /**
     * 期望薪资
     */
    @ExcelProperty("期望薪资")
    private String expectedSalary;

    /**
     * 工作状态
     */
    @ExcelProperty("工作状态")
    private Integer jobStatus;

    /**
     * 目前岗位
     */
    @ExcelProperty("目前岗位")
    private String currentPost;

    /**
     * 地址
     */
    @ExcelProperty("所在地")
    private String address;

    /**
     * 离职原因
     */
    @ExcelProperty("离职原因")
    private String reasonForLeaving;

    /**
     * 优势
     */
    @ExcelProperty("优势")
    private String advantage;

    /**
     * 劣势
     */
    @ExcelProperty("劣势")
    private String disadvantage;



}
