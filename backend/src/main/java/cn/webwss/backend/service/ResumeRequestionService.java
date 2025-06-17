package cn.webwss.backend.service;

import cn.webwss.backend.model.entity.ResumeRequestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【resume_requestion】的数据库操作Service
* @createDate 2025-06-16 10:39:29
*/
public interface ResumeRequestionService extends IService<ResumeRequestion> {

    /**
     * 根据简历ID生成面试题目
     *
     * @param resumeId
     * @return
     */
    Boolean generateQuestions(Integer resumeId);

    /**
     * 获取面试题目
     * @param resumeId
     * @return
     */
    ResumeRequestion getQuestions(Integer resumeId);

}
