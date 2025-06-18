package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.vo.data.AntVO;
import cn.webwss.backend.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {


    @Resource
    private DataService dataService;


    /**
     * 人才数量（hr）
     */
    @PostMapping("/talentCount")
    public BaseResponse<Long> talentCount(HttpServletRequest request) {
        Long result = dataService.talentCount(request);
        return ResultUtils.success(result);
    }


    /**
     * 人才数量（管理员）
     */
    @GetMapping("/totalTalentCount")
    public BaseResponse<Long> totalTalentCount() {
        Long result = dataService.totalTalentCount();
        return ResultUtils.success(result);
    }


    /**
     * 不同岗位人才数量（hr）
     */
    @PostMapping("/postTalentCount")
    public BaseResponse<List<AntVO>> postTalentCount(HttpServletRequest request) {
        List<AntVO> result = dataService.postTalentCount(request);
        return ResultUtils.success(result);
    }

    /**
     * 不同岗位人才数量（管理员）
     */
    @GetMapping("/totalPostTalentCount")
    public BaseResponse<List<AntVO>> totalPostTalentCount() {
        List<AntVO> result = dataService.totalPostTalentCount();
        return ResultUtils.success(result);
    }


    /**
     * 性别及人才数量（hr）
     */
    @PostMapping("/sexTalentCount")
    public BaseResponse<List<AntVO>> sexTalentCount(HttpServletRequest request) {
        List<AntVO> result = dataService.sexTalentCount(request);
        return ResultUtils.success(result);
    }

    /**
     * 性别及人才数量（管理员）
     */
    @GetMapping("/totalSexTalentCount")
    public BaseResponse<List<AntVO>> totalSexTalentCount() {
        List<AntVO> result = dataService.totalSexTalentCount();
        return ResultUtils.success(result);
    }


    /**
     * 学历分布及人才数量（hr）
     */
    @PostMapping("/educationTalentCount")
    public BaseResponse<List<AntVO>> educationTalentCount(HttpServletRequest request) {
        List<AntVO> result = dataService.educationTalentCount(request);
        return ResultUtils.success(result);
    }


    /**
     * 学历分布及人才数量（管理员）
     */
    @GetMapping("/totalEducationTalentCount")
    public BaseResponse<List<AntVO>> totalEducationTalentCount() {
        List<AntVO> result = dataService.totalEducationTalentCount();
        return ResultUtils.success(result);
    }

    /**
     * 近7日新增人才数量趋势图（hr）
     */
    @PostMapping("/newTalentCount")
    public BaseResponse<List<AntVO>> newTalentCount(HttpServletRequest request) {
        List<AntVO> result = dataService.newTalentCount(request);
        return ResultUtils.success(result);
    }


    /**
     * 近7日新增人才数量趋势图（管理员）
     */
    @GetMapping("/totalNewTalentCount")
    public BaseResponse<List<AntVO>> totalNewTalentCount() {
        List<AntVO> result = dataService.totalNewTalentCount();
        return ResultUtils.success(result);
    }


    /**
     * 获取批量上传参考文档
     */
    @GetMapping("/uploadBathData")
    public BaseResponse<HashMap<String, Object>>uploadBathData() {
        HashMap<String, Object> result = dataService.uploadBathData();
        return ResultUtils.success(result);
    }

}
