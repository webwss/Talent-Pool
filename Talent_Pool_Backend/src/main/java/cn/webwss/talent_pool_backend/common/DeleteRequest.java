package cn.webwss.talent_pool_backend.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Integer id;

    private static final long serialVersionUID = 1L;
}
