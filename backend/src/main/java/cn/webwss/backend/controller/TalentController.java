package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.dto.talent.TalentAddDTO;
import cn.webwss.backend.model.dto.talent.TalentDeleteDTO;
import cn.webwss.backend.model.dto.talent.TalentEditBaseInfoDTO;
import cn.webwss.backend.model.dto.talent.TalentRequestDTO;
import cn.webwss.backend.model.entity.Talent;
import cn.webwss.backend.model.vo.talent.TalentVO;
import cn.webwss.backend.service.StaffService;
import cn.webwss.backend.service.TalentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/talent")
public class TalentController {


    @Resource
    private TalentService talentService;
    @Resource
    private StaffService staffService;


    /**
     * 获取人才列表根据员工id
     */
    @PostMapping("/list")
    public BaseResponse<Page<Talent>> getList(@RequestBody TalentRequestDTO talentRequestDTO, HttpServletRequest request) {
        int current = talentRequestDTO.getCurrent();
        int pageSize = talentRequestDTO.getPageSize();
        Page<Talent>  talentPage = talentService.page(new Page<>(current, pageSize),talentService.getQueryWrapper(talentRequestDTO,request));
        return ResultUtils.success(talentPage);
    }


    /**
     * 获取人才详情
     */
    @PostMapping("/detail")
    public BaseResponse<TalentVO> getTalentDetail(@RequestParam("talentId") Integer talentId) {
        TalentVO talentDetail = talentService.getTalentDetail(talentId);
        return ResultUtils.success(talentDetail);
    }


    /**
     * 新增人才信息
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addTalent(TalentAddDTO talentAddDTO,
                                           HttpServletRequest request,
                                           @RequestParam("photo")MultipartFile photo,
                                           @RequestParam(value = "resume", required = false) MultipartFile[] resumes) throws IOException {
        Boolean result = talentService.addTalent(talentAddDTO, request, photo, resumes);
        return ResultUtils.success(result);
    }

    /**
     * 批量新增人才信息
     */
    @PostMapping("/addBatch")
    public BaseResponse<Boolean> addTalentBatch(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        Boolean result = talentService.addTalentBatch(file, request);
        return ResultUtils.success(result);
    }

    /**
     * 编辑人才基本信息
     */
    @PostMapping("/editBaseInfo")
    public BaseResponse<Boolean> editBaseInfo(@RequestBody TalentEditBaseInfoDTO talentEditBaseInfoDTO, HttpServletRequest request) {
        Boolean result = talentService.editBaseInfo(talentEditBaseInfoDTO, request);
        return ResultUtils.success(result);
    }

    /**
     * 编辑人才头像
     */
    @PostMapping("/editPhoto")
    public BaseResponse<Boolean> editPhoto(@RequestParam("talentId") Integer talentId, @RequestParam("photo") MultipartFile photo, HttpServletRequest request) throws IOException {
        Boolean result = talentService.editPhoto(talentId, photo, request);
        return ResultUtils.success(result);
    }


    /**
     * 发送删除请求
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTalent(@RequestBody TalentDeleteDTO talentDeleteDTO) {
        Boolean result = talentService.deleteTalent(talentDeleteDTO);
        return ResultUtils.success(result);
    }


    /**
     * 获取删除列表
     */
    @PostMapping("/getDeleteList")
    public BaseResponse<Page<Talent>> getDeleteList(@RequestBody TalentRequestDTO talentRequestDTO, HttpServletRequest request) {
        int current = talentRequestDTO.getCurrent();
        int pageSize = talentRequestDTO.getPageSize();
        Page<Talent> talentPage = talentService.page(new Page<>(current, pageSize), talentService.getDelQueryWrapper(talentRequestDTO, request));
        return ResultUtils.success(talentPage);
    }


    /**
     * 同意删除
     */
    @PostMapping("/agreeDelete")
    public BaseResponse<Boolean> agreeDelete(@RequestParam("talentId") Integer talentId) {
        Boolean result = talentService.agreeDelete(talentId);
        return ResultUtils.success(result);
    }

    /**
     * 拒绝删除
     */
    @PostMapping("/refuseDelete")
    public BaseResponse<Boolean> refuseDelete(@RequestParam("talentId") Integer talentId) {
        Boolean result = talentService.refuseDelete(talentId);
        return ResultUtils.success(result);
    }






}
