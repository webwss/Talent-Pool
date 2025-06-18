package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.dto.interview.InterviewAddDTO;
import cn.webwss.backend.model.dto.interview.InterviewEditDTO;
import cn.webwss.backend.model.entity.Interview;
import cn.webwss.backend.service.InterviewService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {


    @Resource
    private InterviewService interviewService;

    /**
     * 获取面试列表根据人才id
     */
    @PostMapping("/list")
    public BaseResponse<List<Interview>> getListByTalentId (@RequestParam("talentId") Integer talentId) {
        List<Interview> list = interviewService.getListByTalentId(talentId);
        return ResultUtils.success(list);
    }

    /**
     * 获取面试详情
     */
    @PostMapping("/detail")
    public BaseResponse<Interview> getInterviewDetail (@RequestParam("interviewId") Integer interviewId) {
        Interview interviewDetail = interviewService.getInterviewDetail(interviewId);
        return ResultUtils.success(interviewDetail);
    }


    /**
     * 新增面试信息
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addInterview (@RequestBody InterviewAddDTO interviewAddDTO) {
        Boolean result =  interviewService.addInterview(interviewAddDTO);
        return ResultUtils.success(result);
    }


    /**
     * 编辑面试信息
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editInterview (@RequestBody InterviewEditDTO interviewEditDTO) {
        Boolean result = interviewService.editInterview(interviewEditDTO);
        return ResultUtils.success(result);
    }



    /**
     * 删除面试信息
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterview (@RequestParam("interviewId") Integer interviewId) {
        Boolean result = interviewService.deleteInterview(interviewId);
        return ResultUtils.success(result);
    }




}
