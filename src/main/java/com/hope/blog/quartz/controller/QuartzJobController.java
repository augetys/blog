package com.hope.blog.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.common.exception.BusinessException;
import com.hope.blog.log.handle.OperationLog;
import com.hope.blog.quartz.dto.request.JobUpdateStatusRequestDto;
import com.hope.blog.quartz.model.QuartzJob;
import com.hope.blog.quartz.model.QuartzLog;
import com.hope.blog.quartz.service.QuartzJobService;
import com.hope.blog.quartz.dto.request.JobQueryRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author lijin
 * @since 2021-07-02
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/task")
@Api(tags = "定时任务管理")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";
    private final QuartzJobService quartzJobService;

    @ApiOperation("查询定时任务")
    @PostMapping("/jobs")
    public CommonResult<CommonPage<QuartzJob>> query(@ApiParam @RequestBody JobQueryRequestDto criteria) {
        IPage<QuartzJob> list = quartzJobService.findJobByPage(criteria);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("查询任务执行日志")
    @PostMapping("/logs")
    public CommonResult<CommonPage<QuartzLog>> queryJobLog(@ApiParam @RequestBody JobQueryRequestDto criteria) {
        IPage<QuartzLog> list = quartzJobService.findLogByPage(criteria);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @OperationLog("新增定时任务")
    @ApiOperation("新增定时任务")
    @PostMapping("/create")
    public CommonResult<ResultCode> create(@ApiParam @RequestBody @Valid QuartzJob quartzJob) {
        if (quartzJob.getId() != null) {
            throw new BusinessException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        quartzJobService.create(quartzJob);
        return CommonResult.success();
    }

    @OperationLog("修改定时任务")
    @ApiOperation("修改定时任务")
    @PostMapping("/update")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid QuartzJob quartzJob) {
        quartzJobService.update(quartzJob);
        return CommonResult.success();
    }

    @OperationLog("更改定时任务状态")
    @ApiOperation("更改定时任务状态")
    @PostMapping("/updateStatus")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid JobUpdateStatusRequestDto jobUpdateStatusRequestDto) {
        quartzJobService.updateIsPause(jobUpdateStatusRequestDto);
        return CommonResult.success();
    }

    @OperationLog("执行定时任务")
    @ApiOperation("执行定时任务")
    @GetMapping("/exec/{id}")
    public CommonResult<ResultCode> execution(@ApiParam @PathVariable String id) {
        quartzJobService.execution(quartzJobService.findById(id));
        return CommonResult.success(null,"执行成功，请前往定时任务日志查看运行结果！");
    }

    @OperationLog("删除定时任务")
    @ApiOperation("删除定时任务")
    @GetMapping("/delete/{id}")
    public CommonResult<ResultCode> delete(@ApiParam @PathVariable String id) {
        quartzJobService.delete(id);
        return CommonResult.success();
    }
}
