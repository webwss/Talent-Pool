package cn.webwss.talent_pool_backend.controller;

import cn.webwss.talent_pool_backend.annotation.ApiOperationLog;
import cn.webwss.talent_pool_backend.common.BaseResponse;
import cn.webwss.talent_pool_backend.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/health")
    @ApiOperationLog(description = "健康检查")
    public BaseResponse<String> health() {
        return ResultUtils.success("success");
    }



}
