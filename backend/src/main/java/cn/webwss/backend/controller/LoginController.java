package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.entity.Staff;
import cn.webwss.backend.service.StaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private StaffService staffService;


    /**
     * 企业微信扫码登录
     */
    @PostMapping
    public BaseResponse<Staff> wxLogin(@RequestParam("code") String code, HttpServletRequest request) throws Exception {
        return ResultUtils.success(staffService.wxLogin(code,  request));
    }

    /**
     * 校验是否登录
     */
    @PostMapping("/check")
    public BaseResponse<Staff> checkToken(HttpServletRequest request) {
        return ResultUtils.success(staffService.checkToken(request));
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public BaseResponse<Boolean> logout(HttpServletRequest request) {
        return ResultUtils.success(staffService.logout(request));
    }

}
