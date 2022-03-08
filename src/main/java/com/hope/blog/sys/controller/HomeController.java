package com.hope.blog.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.sys.service.HomeService;
import io.swagger.annotations.Api;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


/**
 * Created by lijin on  2021/12/31
 */
@Api(tags = "首页面板")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private HomeService homeService;

    /**
     * 获取天气信息
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取天气信息")
    @RequestMapping("/getWeather")
    public CommonResult<JSONObject> getWeather() throws Exception {
        JSONObject jsonObject = homeService.getWeather();
        return CommonResult.success(jsonObject);
    }

    /**
     * 获取推荐书籍
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取推荐书籍")
    @RequestMapping("/getBook")
    public CommonResult<JSONObject> getBook() throws Exception {
        JSONObject jsonObject = homeService.getBook();
        return CommonResult.success(jsonObject);
    }

    /**
     * 获取老黄历
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取老黄历")
    @RequestMapping("/getDay")
    public CommonResult<JSONObject> getDay() throws Exception{
        JSONObject jsonObject = homeService.getDay();
        return CommonResult.success(jsonObject);
    }

    /**
     * 获取星座运势
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取星座运势")
    @RequestMapping("/getConstellation")
    public CommonResult<JSONObject> getConstellation() throws Exception{
        JSONObject jsonObject = homeService.getConstellation();
        return CommonResult.success(jsonObject);
    }


    /**
     * 获取毒鸡汤
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取毒鸡汤")
    @RequestMapping("/getSoul")
    public CommonResult<String> getSoul() throws Exception{
        String soul = homeService.getSoul();
        return CommonResult.success(soul);
    }

    /**
     * 获取历史上的今天
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取历史上的今天")
    @RequestMapping("/getHistory")
    public CommonResult<JSONObject> getHistory() throws Exception{
        JSONObject jsonObject = homeService.getHistory();
        return CommonResult.success(jsonObject);
    }

    /**
     * 获取统计数据
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取统计数据")
    @RequestMapping("/getTotal")
    public CommonResult<HashMap<String, Integer>> getTotal() throws Exception{
        HashMap<String, Integer> hashMap = homeService.getTotal();
        return CommonResult.success(hashMap);
    }
}
