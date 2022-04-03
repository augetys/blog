package com.hope.blog.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hope.blog.blog.service.BlogArticleService;
import com.hope.blog.blog.service.BlogSoulService;
import com.hope.blog.blog.service.BlogTagService;
import com.hope.blog.comment.service.BlogCommentService;
import com.hope.blog.common.exception.BusinessException;
import com.hope.blog.quartz.service.QuartzJobService;
import com.hope.blog.resource.service.LocalStorageService;
import com.hope.blog.resource.service.QiniuService;
import com.hope.blog.sys.service.HomeService;
import com.hope.blog.sys.service.SysMenusService;
import com.hope.blog.sys.service.SysUserService;
import com.hope.blog.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by lijin on  2021/12/31
 */
@Service
@Transactional
@Slf4j
public class HomeServiceImpl implements HomeService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private QuartzJobService quartzJobService;

    @Resource
    private SysMenusService sysMenusService;

    @Resource
    private LocalStorageService localStorageService;

    @Resource
    private QiniuService qiniuService;

    @Resource
    private BlogArticleService blogArticleService;

    @Resource
    private BlogTagService blogTagService;

    @Resource
    private BlogCommentService blogCommentService;

    @Resource
    private BlogSoulService blogSoulService;

    // 配置天气API KEY 此key来源于free api
    public static final String WeatherKey = "251518e073ef6c3c9504dd286c3f6a86";
    // 配置星座运势API KEY 此key来源于聚合数据
    public static final String ConstellationKey = "b9ad0e3a6e6465aaeede2fbfe7c57ee6";
    // 配置老黄历API KEY 此key来源于聚合数据
    public static final String DayKey = "87533592b7f5ed449ca61c4319dcd1a0";
    // 配置图书API KEY 此key来源于聚合数据
    public static final String BookKey = "da5d44edba3f8e9a577d0cd818d38398";


    public static final String SoulKey = "LwExDtUWhF3rH5ib";

    @Override
    public JSONObject getWeather() throws Exception {
        // 请求接口地址
        String url = "https://apis.juhe.cn/simpleWeather/query";
        // 要查询的城市，如：温州、上海、北京
        HttpServletRequest request = HttpContextUtil.getRequest();
        String ip = IPUtil.getIpAddr(request);
        log.info("ip地址为：{}", ip);
        String cityInfo = IPUtil.getCityInfo(ip);
        log.info("所在城市为：{}", cityInfo);
        String result = HttpUtil.builder().url(url).addParam("city", getCityName(cityInfo)).addParam("key", WeatherKey).get().sync();
        JSONObject object = JSONObject.parseObject(result);
        if ((Integer) object.get("error_code") == 0) {
            return object;
        } else {
            log.error("从第三方api获取天气数据失败：{}", object.get("reason"));
            throw new BusinessException("获取天气数据失败！");
        }
    }

    @Override
    public JSONObject getDay() throws Exception {
        // 请求接口地址
        String url = "http://v.juhe.cn/laohuangli/d";
        String result = HttpUtil.builder().url(url).addParam("date", DateUtil.format(new Date(), DateUtil.DATE_FORMAT_DAY)).addParam("key", DayKey).get().sync();
        JSONObject object = JSONObject.parseObject(result);
        if ((Integer) object.get("error_code") == 0) {
            return object;
        } else {
            log.error("从第三方api获取老黄历数据失败：{}", object.get("reason"));
            throw new BusinessException("获取老黄历数据失败！");
        }
    }

    @Override
    public JSONObject getConstellation() throws Exception {
        // 请求接口地址
        String url = "http://web.juhe.cn/constellation/getAll";
        String result = HttpUtil.builder().url(url).addParam("type", "today").addParam("key", ConstellationKey).addParam("consName", "天秤座").get().sync();
        JSONObject object = JSONObject.parseObject(result);
        if ((Integer) object.get("error_code") == 0) {
            return object;
        } else {
            log.error("从第三方api获取星座运势数据失败：{}", object.get("reason"));
            throw new BusinessException("获取星座运势数据失败！");
        }
    }

    @Override
    public String getSoul() throws Exception {
        // 请求接口地址
        String url = "https://api.oick.cn/dutang/api.php";
        return HttpUtil.builder().url(url).addParam("token", SoulKey).get().sync();
    }

    @Override
    public JSONObject getHistory() throws Exception {
        // 请求接口地址
        String url = "https://api.oick.cn/lishi/api.php";
        String result = HttpUtil.builder().url(url).get().sync();
        JSONObject object = JSONObject.parseObject(result);
        if ("1".equals(object.get("code"))) {
            return object;
        } else {
            log.error("从第三方api获取历史上的今天数据失败：{}", object.get("reason"));
            throw new BusinessException("获取历史上的今天数据失败！");
        }
    }

    @Override
    public HashMap<String, Integer> getTotal() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("articleCount", blogArticleService.count());
        hashMap.put("tagCount", blogTagService.count());
        hashMap.put("commentCount", blogCommentService.count());
        hashMap.put("soulCount", blogSoulService.count());
        hashMap.put("userCount", sysUserService.count());
        hashMap.put("jobCount", quartzJobService.count());
        hashMap.put("menuCount", sysMenusService.count());
        hashMap.put("fileCount", localStorageService.count() + qiniuService.count());
        return hashMap;
    }

    @Override
    public JSONObject getBook() throws Exception {
        // 请求接口地址
        String url = "http://apis.juhe.cn/goodbook/query";
        // 	目录编号
        int[] arr = {242, 252, 244, 248, 257, 243, 247, 251, 253, 250, 249, 245, 256, 254, 246, 255, 258};
        String result = HttpUtil.builder().url(url).addParam("catalog_id", String.valueOf(arr[(int) (Math.random() * arr.length)])).addParam("key", BookKey).addParam("pn", "1").addParam("rn", "1").get().sync();
        JSONObject object = JSONObject.parseObject(result);
        if ((Integer) object.get("error_code") == 0) {
            return object;
        } else {
            log.error("从第三方api获取图书数据失败：{}", object.get("reason"));
            throw new BusinessException("获取图书数据失败！");
        }
    }

    private String getCityName(String cityInfo) {
        String activeProfile = SpringContextHolder.getActiveProfile();
        String cityName = activeProfile.equals("pro") ? cityInfo.split("\\|")[2] : cityInfo.split("\\|")[1];
        if (cityName.equals("内网IP")) {
            return "武汉";
        } else {
            return cityName;
        }
    }
}
