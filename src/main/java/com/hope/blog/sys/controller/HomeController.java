package com.hope.blog.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.sys.service.HomeService;
import io.swagger.annotations.Api;

import javax.annotation.Resource;

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

    @RequestMapping("/getWeather")
    public CommonResult<JSONObject> getWeather() throws Exception {
        JSONObject jsonObject = homeService.getWeather();
        return CommonResult.success(jsonObject);
    }

    @RequestMapping("/getBook")
    public CommonResult<JSONObject> getBook() throws Exception {
        JSONObject jsonObject = homeService.getBook();
        return CommonResult.success(jsonObject);
    }

    @RequestMapping("/getDay")
    public CommonResult<JSONObject> getDay() throws Exception{
        JSONObject jsonObject = homeService.getDay();
        return CommonResult.success(jsonObject);
    }

    @RequestMapping("/getConstellation")
    public CommonResult<JSONObject> getConstellation() throws Exception{
        JSONObject jsonObject = homeService.getConstellation();
        return CommonResult.success(jsonObject);
    }

    @RequestMapping("/getSoul")
    public CommonResult<String> getSoul() throws Exception{
        String soul = homeService.getSoul();
        return CommonResult.success(soul);
    }

    @RequestMapping("/getHistory")
    public CommonResult<JSONObject> getHistory() throws Exception{
        JSONObject jsonObject = homeService.getHistory();
        return CommonResult.success(jsonObject);
    }

    @RequestMapping("/getTotal")
    public CommonResult<HashMap<String, Integer>> getTotal() throws Exception{
        HashMap<String, Integer> hashMap = homeService.getTotal();
        return CommonResult.success(hashMap);
    }
}
