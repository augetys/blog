package com.hope.blog.monitor.controller;

import com.hope.blog.common.api.CommonResult;
import com.hope.blog.monitor.ServerInfo.ServerInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Create by lijin on 2021/6/19 11:44
 */
@Api(tags = "服务器信息")
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @ApiOperation(value = "获取服务信息", notes = "获取服务信息")
    @GetMapping("/getServerInfo")
    public CommonResult<ServerInfo> getInfo() {
        ServerInfo server = new ServerInfo();
        server.copyTo();
        return CommonResult.success(server);
    }
}
