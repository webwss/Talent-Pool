package cn.webwss.backend.model.dto.post;

import lombok.Data;

@Data
public class PostEditDTO {

    /**
     * 岗位id
     */
    private Integer postId;

    /**
     * 岗位名称
     */
    private String postName;

}
