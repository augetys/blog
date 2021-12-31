package com.hope.blog.sys.service;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * Created by lijin on  2021/12/31
 */
public interface HomeService {

    JSONObject getWeather() throws Exception;

    JSONObject getDay() throws Exception;

    JSONObject getConstellation() throws Exception;

    String getSoul() throws Exception;

    JSONObject getHistory() throws Exception;

    HashMap<String, Integer> getTotal() throws Exception;

    JSONObject getBook() throws Exception;

}
