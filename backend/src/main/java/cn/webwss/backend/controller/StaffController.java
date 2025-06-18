package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.dto.staff.StaffEditDTO;
import cn.webwss.backend.model.entity.Staff;
import cn.webwss.backend.service.StaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {


    @Resource
    private StaffService staffService;

    /**
     * 获取员工列表
     */
    @GetMapping("/list")
    public BaseResponse<List<Staff>> getList() {
        List<Staff> list = staffService.list();
        return ResultUtils.success(list);
    }


    /**
     * 编辑员工信息
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editStaff(@RequestBody StaffEditDTO staffEditDTO) {
        boolean result = staffService.editStaff(staffEditDTO);
        return ResultUtils.success(result);
    }



    /**
     * 删除员工信息
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteStaff(@RequestParam("staffId") Integer staffId) {
        boolean result = staffService.deleteStaff(staffId);
        return ResultUtils.success(result);
    }

    /**
     * 获取员工信息
     */
    @PostMapping("/detail")
    public BaseResponse<Staff> getStaffDetail(@RequestParam("staffId") Integer staffId) {
        Staff staff = staffService.getById(staffId);
        return ResultUtils.success(staff);
    }



}