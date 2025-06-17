package cn.webwss.backend.service;

import cn.webwss.backend.model.dto.interview.InterviewAddDTO;
import cn.webwss.backend.model.dto.interview.InterviewEditDTO;
import cn.webwss.backend.model.entity.Interview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【interview】的数据库操作Service
* @createDate 2025-06-16 10:39:29
*/
public interface InterviewService extends IService<Interview> {


    /**
     * 根据人才id获取面试记录
     * @param talentId
     * @return
     */
    List<Interview> getListByTalentId(Integer talentId);

    /**
     * 添加面试记录
     * @param interviewAddDTO
     * @return
     */
    Boolean addInterview(InterviewAddDTO interviewAddDTO);


    /**
     * 修改面试记录
     * @param interviewEditDTO
     * @return
     */
    Boolean editInterview(InterviewEditDTO interviewEditDTO);


    /**
     * 删除面试记录
     * @param interviewId
     * @return
     */
    Boolean deleteInterview(Integer interviewId);


    /**
     * 获取面试记录详情
     * @param interviewId
     * @return
     */
    Interview getInterviewDetail(Integer interviewId);

}
