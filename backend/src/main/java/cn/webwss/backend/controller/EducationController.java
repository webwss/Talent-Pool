package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.dto.education.EducationEditDTO;
import cn.webwss.backend.model.entity.Education;
import cn.webwss.backend.service.EducationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {


    @Resource
    private EducationService educationService;


    /**
     * 获取学历列表
     */
    @GetMapping("/list")
    public BaseResponse<List<Education>> getEducationList(){
        return ResultUtils.success(educationService.list());
    }



    /**
     * 新增学历信息
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addEducation(@RequestParam("educationName") String educationName){
        Boolean result = educationService.addEducation(educationName);
        return ResultUtils.success(result);
    }



    /**
     * 编辑学历信息
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editEducation(@RequestBody EducationEditDTO educationEditDTO) {
        Boolean result = educationService.editEducation(educationEditDTO);
        return ResultUtils.success(result);
    }


    /**
     * 删除学历信息
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEducation(@RequestParam("educationId") Integer educationId) {
        Boolean result = educationService.deleteEducation(educationId);
        return ResultUtils.success(result);
    }


}
