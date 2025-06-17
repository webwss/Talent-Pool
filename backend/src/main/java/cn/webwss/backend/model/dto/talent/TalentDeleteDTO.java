package cn.webwss.backend.model.dto.talent;

import lombok.Data;

@Data
public class TalentDeleteDTO {

    /**
     * 人才id
     */
    private Integer talentId;

    /**
     * 删除原因
     */
    private String delReason;


}
