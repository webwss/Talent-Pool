package cn.webwss.backend.service.impl;

import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.mapper.TalentMapper;
import cn.webwss.backend.model.dto.education.EducationEditDTO;
import cn.webwss.backend.model.entity.Talent;
import cn.webwss.backend.service.EducationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webwss.backend.model.entity.Education;
import cn.webwss.backend.mapper.EducationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author admin
* @description 针对表【education】的数据库操作Service实现
* @createDate 2025-06-16 10:39:29
*/
@Service
public class EducationServiceImpl extends ServiceImpl<EducationMapper, Education>
    implements EducationService {


    @Resource
    private TalentMapper talentMapper;

    /**
     * 添加学历
     * @param educationName
     * @return
     */
    @Override
    public Boolean addEducation(String educationName) {
        Education education = new Education();
        education.setEducationName(educationName);
        boolean save = save(education);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "学历添加失败");
        }
        return true;
    }

    /**
     * 删除学历
     * @param educationId
     * @return
     */
    @Override
    public Boolean deleteEducation(Integer educationId) {
        if (educationId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
//        查询Talent中是否有该学历
        QueryWrapper<Talent> talentQueryWrapper = new QueryWrapper<>();
        talentQueryWrapper.eq("education_id", educationId);
        Long l = talentMapper.selectCount(talentQueryWrapper);
        if (l > 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "该学历有人才使用，请勿删除");
        }
        boolean removeById = removeById(educationId);
        if (!removeById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "学历删除失败");
        }
        return true;
    }

    /**
     * 修改学历
     * @param educationEditDTO
     * @return
     */
    @Override
    public Boolean editEducation(EducationEditDTO educationEditDTO) {
        Integer educationId = educationEditDTO.getEducationId();
        String educationName = educationEditDTO.getEducationName();
        if (educationId == null || educationName == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Education education = new Education();
        education.setEducationId(educationId);
        education.setEducationName(educationName);
        boolean update = updateById(education);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "学历修改失败");
        }
        return true;
    }
}




