package cn.webwss.backend.model.dto.talent;

import cn.webwss.backend.common.PageRequest;
import lombok.Data;

@Data
public class TalentRequestDTO extends PageRequest {


    /**
     * 姓名
     */
    private String name;

    /**
     * 岗位id
     */
    private Integer postId;

    /**
     * 学历id
     */
    private Integer educationId;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 性别（1-男，2-女）
     */
    private Integer sex;


    /**
     * 婚育情况（1-已婚已育，2-已婚未育，3-未婚未育）
     */
    private Integer marriage;


    /**
     * 工作状态（1-在职，2-离职）
     */
    private Integer jobStatus;


    /**
     * 面试结果（1-待定，2-通过，3-未通过）
     */
    private Integer result;


    /**
     * 多字段搜索
     */
    private String searchText;

    /**
     * 删除审核
     */
    private Integer deleteStatus;


}
