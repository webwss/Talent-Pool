package cn.webwss.backend.mapper;

import java.util.List;
import java.util.Map;

public interface DataMapper {

    /**
     * 获取各岗位人才数量
     * @param staffId
     * @return
     */
    List<Map<String, Object>> postTalentCount(Integer staffId);


    /**
     * 获取各岗位人才数量
     * @return
     */
    List<Map<String, Object>> totalPostTalentCount();


    /**
     * 获取各学历人才数量
     * @param staffId
     * @return
     */
    List<Map<String, Object>> educationTalentCount(Integer staffId);

    /**
     * 获取各学历人才数量
     * @return
     */
    List<Map<String, Object>> totalEducationTalentCount();
}
