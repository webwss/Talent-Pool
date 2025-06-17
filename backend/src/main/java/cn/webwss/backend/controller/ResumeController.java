package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.dto.resume.ResumeAddDTO;
import cn.webwss.backend.model.entity.Resume;
import cn.webwss.backend.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Resource
    private ResumeService resumeService;

    /**
     * 获取简历列表根据人才id
     */
    @PostMapping("/list")
    public BaseResponse<List<Resume>> getList(@RequestParam("talentId") Integer talentId) {
        List<Resume> list = resumeService.getList(talentId);
        return ResultUtils.success(list);
    }


    /**
     * 新增简历信息
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addResume(@RequestBody ResumeAddDTO resumeAddDTO) {
        Boolean result = resumeService.addResume(resumeAddDTO);
        return ResultUtils.success(result);
    }


    /**
     * 新增简历信息
     */
    @PostMapping("/addResume")
    public BaseResponse<Boolean> addResumes(@RequestParam("talentId") Integer talentId,
                                           @RequestParam("resume")MultipartFile[] resume) throws IOException {
        Boolean result = resumeService.addResumes(talentId, resume);
        return ResultUtils.success(result);
    }

    /**
     * 删除简历信息
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteResume(@RequestParam("resumeId") Integer resumeId) {
        Boolean result = resumeService.deleteResume(resumeId);
        return ResultUtils.success(result);
    }


}
