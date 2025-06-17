package cn.webwss.backend.service.impl;
import java.util.Date;

import cn.hutool.core.util.StrUtil;
import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.mapper.InterviewMapper;
import cn.webwss.backend.model.dto.interview.InterviewAddDTO;
import cn.webwss.backend.model.dto.interview.InterviewEditDTO;
import cn.webwss.backend.model.entity.Interview;
import cn.webwss.backend.service.InterviewService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @description 针对表【interview】的数据库操作Service实现
 * @createDate 2025-05-27 11:11:47
 */
@Service
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview>
        implements InterviewService {


    /**
     * 根据人才id查询面试记录
     * @param talentId
     * @return
     */
    @Override
    public List<Interview> getListByTalentId(Integer talentId) {
        if (talentId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<Interview> interviewQueryWrapper = new QueryWrapper<>();
        interviewQueryWrapper.eq("talent_id", talentId);
        interviewQueryWrapper.orderByAsc("interview_time");
        return list(interviewQueryWrapper);
    }


    /**
     * 添加面试记录
     * @param interviewAddDTO
     * @return
     */
    @Override
    public Boolean addInterview(InterviewAddDTO interviewAddDTO) {
        Integer talentId = interviewAddDTO.getTalentId();
        String interviewer = interviewAddDTO.getInterviewer();
        String interviewTime = interviewAddDTO.getInterviewTime();
        String interviewAccess = interviewAddDTO.getInterviewAccess();
        if (StrUtil.hasBlank(interviewer, interviewAccess)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (talentId == null || interviewTime == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Interview interview = new Interview();
        interview.setTalentId(talentId);
        interview.setInterviewer(interviewer);
        interview.setInterviewTime(interviewTime);
        interview.setInterviewAccess(interviewAccess);
        boolean save = save(interview);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "面试记录添加失败");
        }
        return true;
    }


    /**
     * 编辑面试记录
     * @param interviewEditDTO
     * @return
     */
    @Override
    public Boolean editInterview(InterviewEditDTO interviewEditDTO) {
        Integer interviewId = interviewEditDTO.getInterviewId();
        String interviewer = interviewEditDTO.getInterviewer();
        String interviewTime = interviewEditDTO.getInterviewTime();
        String interviewAccess = interviewEditDTO.getInterviewAccess();
        if (StrUtil.hasBlank(interviewer, interviewAccess)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (interviewId == null || interviewTime == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Interview interview = new Interview();
        interview.setInterviewId(interviewId);
        interview.setInterviewer(interviewer);
        interview.setInterviewTime(interviewTime);
        interview.setInterviewAccess(interviewAccess);
        boolean update = updateById(interview);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "面试记录修改失败");
        }
        return true;
    }


    /**
     * 修改面试记录
     * @param interviewId
     * @return
     */
    @Override
    public Boolean deleteInterview(Integer interviewId) {
        if (interviewId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean removeById = removeById(interviewId);
        if (!removeById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "面试记录删除失败");
        }
        return true;
    }


    /**
     * 获取面试记录详情
     * @param interviewId
     * @return
     */
    @Override
    public Interview getInterviewDetail(Integer interviewId) {
        if (interviewId == null || interviewId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Interview interview = getById(interviewId);
        if (interview == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "面试记录不存在");
        }
        return interview;
    }
}




