package cn.webwss.backend.service;

import cn.webwss.backend.model.dto.interact.InteractAddDTO;
import cn.webwss.backend.model.dto.interact.InteractEditDTO;
import cn.webwss.backend.model.entity.Interact;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【interact】的数据库操作Service
* @createDate 2025-06-16 10:39:29
*/
public interface InteractService extends IService<Interact> {

    /**
     * 通过人才id获取沟通记录列表
     * @param talentId
     * @return
     */
    List<Interact> getListByTalentId(Integer talentId);

    /**
     * 添加沟通记录
     * @param interactAddDTO
     * @return
     */
    Boolean addInteract(InteractAddDTO interactAddDTO);


    /**
     * 编辑沟通记录
     * @param interactEditDTO
     * @return
     */
    Boolean editInteract(InteractEditDTO interactEditDTO);


    /**
     * 删除沟通记录
     * @param interactId
     * @return
     */
    Boolean deleteInteract(Integer interactId);

    /**
     * 获取沟通记录详情
     * @param interactId
     * @return
     */
    Interact getInteractDetail(Integer interactId);


}
