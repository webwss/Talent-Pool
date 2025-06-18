package cn.webwss.backend.service;


import cn.webwss.backend.model.vo.data.AntVO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface DataService {

    /**
     * 人才数量（hr）
     * @param request
     * @return
     */
    Long talentCount(HttpServletRequest request);

    /**
     * 人才数量（管理员）
     * @return
     */
    Long totalTalentCount();

    /**
     * 不同岗位人才数量（hr）
     * @param request
     * @return
     */
    List<AntVO> postTalentCount(HttpServletRequest request);


    /**
     * 不同岗位人才数量（管理员）
     * @return
     */
    List<AntVO> totalPostTalentCount();

    /**
     * 性别及人才数量（hr）
     * @param request
     * @return
     */
    List<AntVO> sexTalentCount(HttpServletRequest request);

    /**
     * 性别及人才数量（管理员）
     * @return
     */
    List<AntVO> totalSexTalentCount();

    /**
     * 学历及人才数量（hr）
     * @param request
     * @return
     */
    List<AntVO> educationTalentCount(HttpServletRequest request);

    /**
     * 学历及人才数量（管理员）
     * @return
     */
    List<AntVO> totalEducationTalentCount();

    /**
     * 新增人才数量（hr）
     * @param request
     * @return
     */
    List<AntVO> newTalentCount(HttpServletRequest request);


    /**
     * 新增人才数量（管理员）
     * @return
     */
    List<AntVO> totalNewTalentCount();

    /**
     * 批量上传数据
     * @return
     */
    HashMap<String, Object> uploadBathData();
}
