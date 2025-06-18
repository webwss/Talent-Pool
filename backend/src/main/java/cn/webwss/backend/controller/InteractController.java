package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.dto.interact.InteractAddDTO;
import cn.webwss.backend.model.dto.interact.InteractEditDTO;
import cn.webwss.backend.model.entity.Interact;
import cn.webwss.backend.service.InteractService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/interact")
public class InteractController {

    @Resource
    private InteractService interactService;

    /**
     * 获取沟通列表根据人才id
     */
    @PostMapping("/list")
    public BaseResponse<List<Interact>> getListByTalentId (@RequestParam("talentId") Integer talentId) {
        List<Interact> list = interactService.getListByTalentId(talentId);
        return ResultUtils.success(list);
    }

    /**
     * 获取沟通详情
     */
    @PostMapping("/detail")
    public BaseResponse<Interact> getInteractDetail (@RequestParam("interactId") Integer interactId) {
        Interact interactDetail = interactService.getInteractDetail(interactId);
        return ResultUtils.success(interactDetail);
    }


    /**
     * 新增沟通信息
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addInteract (@RequestBody InteractAddDTO interactAddDTO) {
        Boolean result = interactService.addInteract(interactAddDTO);
        return ResultUtils.success(result);
    }


    /**
     * 编辑沟通信息
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editInteract (@RequestBody InteractEditDTO interactEditDTO) {
        Boolean result = interactService.editInteract(interactEditDTO);
        return ResultUtils.success(result);
    }


    /**
     * 删除沟通信息
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInteract (@RequestParam("interactId") Integer interactId) {
        Boolean result = interactService.deleteInteract(interactId);
        return ResultUtils.success(result);
    }




}
