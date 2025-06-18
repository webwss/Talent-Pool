package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.entity.ResumeRequestion;
import cn.webwss.backend.service.ResumeRequestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/resumeRequestion")
public class ResumeRequestionController {


    @Resource
    private ResumeRequestionService resumeRequestionService;

    /**
     * 根据简历ID生成面试题目
     */
    @PostMapping("/generateQuestions")
    public BaseResponse<Boolean> generateQuestions(@RequestParam("resumeId")Integer resumeId){
        Boolean result = resumeRequestionService.generateQuestions(resumeId);
        return ResultUtils.success(result);
    }

    /**
     * 获取面试题目
     */
    @PostMapping("/getQuestions")
    public BaseResponse<ResumeRequestion> getQuestions(@RequestParam("resumeId")Integer resumeId){
        ResumeRequestion resumeRequestion = resumeRequestionService.getQuestions(resumeId);
        return ResultUtils.success(resumeRequestion);
    }


}
