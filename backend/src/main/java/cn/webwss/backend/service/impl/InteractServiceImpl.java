package cn.webwss.backend.service.impl;
import java.util.Date;

import cn.hutool.core.util.StrUtil;
import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.mapper.InteractMapper;
import cn.webwss.backend.model.dto.interact.InteractAddDTO;
import cn.webwss.backend.model.dto.interact.InteractEditDTO;
import cn.webwss.backend.model.entity.Interact;
import cn.webwss.backend.service.InteractService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @description 针对表【interact】的数据库操作Service实现
 * @createDate 2025-05-27 11:11:47
 */
@Service
public class InteractServiceImpl extends ServiceImpl<InteractMapper, Interact>
        implements InteractService {


    /**
     * 根据人才id查询沟通记录列表
     * @param talentId
     * @return
     */
    @Override
    public List<Interact> getListByTalentId(Integer talentId) {
        if (talentId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<Interact> interactQueryWrapper = new QueryWrapper<>();
        interactQueryWrapper.eq("talent_id", talentId);
        interactQueryWrapper.orderByAsc("interact_time");
        return list(interactQueryWrapper);
    }


    /**
     * 添加沟通记录
     * @param interactAddDTO
     * @return
     */
    @Override
    public Boolean addInteract(InteractAddDTO interactAddDTO) {
        Integer talentId = interactAddDTO.getTalentId();
        String interactStaff = interactAddDTO.getInteractStaff();
        String interactTime = interactAddDTO.getInteractTime();
        String interactContent = interactAddDTO.getInteractContent();
        if (StrUtil.hasBlank(interactStaff, interactContent)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (talentId == null || interactTime == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Interact interact = new Interact();
        interact.setTalentId(talentId);
        interact.setInteractStaff(interactStaff);
        interact.setInteractTime(interactTime);
        interact.setInteractContent(interactContent);
        boolean save = save(interact);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "沟通记录添加失败");
        }
        return true;
    }


    /**
     * 修改沟通记录
     * @param interactEditDTO
     * @return
     */
    @Override
    public Boolean editInteract(InteractEditDTO interactEditDTO) {
        Integer interactId = interactEditDTO.getInteractId();
        String interactStaff = interactEditDTO.getInteractStaff();
        String interactTime = interactEditDTO.getInteractTime();
        String interactContent = interactEditDTO.getInteractContent();
        if (StrUtil.hasBlank(interactStaff, interactContent)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (interactId == null || interactTime == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Interact interact = new Interact();
        interact.setInteractId(interactId);
        interact.setInteractStaff(interactStaff);
        interact.setInteractTime(interactTime);
        interact.setInteractContent(interactContent);
        boolean update = updateById(interact);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "沟通记录修改失败");
        }
        return true;
    }


    /**
     * 删除沟通记录
     * @param interactId
     * @return
     */
    @Override
    public Boolean deleteInteract(Integer interactId) {
        if (interactId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean removeById = removeById(interactId);
        if (!removeById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "沟通记录删除失败");
        }
        return true;
    }

    /**
     * 获取沟通记录详情
     * @param interactId
     * @return
     */
    @Override
    public Interact getInteractDetail(Integer interactId) {
        if (interactId == null || interactId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Interact interact = getById(interactId);
        if (interact == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "沟通记录不存在");
        }
        return interact;
    }
}




