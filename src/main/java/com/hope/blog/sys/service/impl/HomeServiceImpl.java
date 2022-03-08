package com.hope.blog.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.model.BlogSoul;
import com.hope.blog.blog.model.BlogTag;
import com.hope.blog.blog.service.BlogArticleService;
import com.hope.blog.blog.service.BlogSoulService;
import com.hope.blog.blog.service.BlogTagService;
import com.hope.blog.comment.model.BlogComment;
import com.hope.blog.comment.service.BlogCommentService;
import com.hope.blog.common.exception.BusinessException;
import com.hope.blog.quartz.service.QuartzJobService;
import com.hope.blog.resource.model.LocalStorage;
import com.hope.blog.resource.model.QiniuContent;
import com.hope.blog.resource.service.LocalStorageService;
import com.hope.blog.resource.service.QiniuService;
import com.hope.blog.sys.service.HomeService;
import com.hope.blog.sys.service.SysMenusService;
import com.hope.blog.sys.service.SysUserService;
import com.hope.blog.utils.DateUtil;
import com.hope.blog.utils.HttpContextUtil;
import com.hope.blog.utils.HttpUtil;
import com.hope.blog.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public JSONObject getWeather() throws Exception {
        // 请求接口地址
        String url = "https://apis.juhe.cn/simpleWeather/query";
        // 请求参数
        Map<String, String> params = new HashMap<>();
        // 要查询的城市，如：温州、上海、北京
        HttpServletRequest request = HttpContextUtil.getRequest();
        String ip = IPUtil.getIpAddr(request);
        log.info("ip地址为：{}", ip);
        String cityInfo = IPUtil.getCityInfo(ip);
        log.info("所在城市为：{}", cityInfo);
        // 如果是手机热点，可能获取的城市info切割有误
        params.put("city", cityInfo.split("\\|")[2]);
        // 应用KEY(应用详细页查询)
        params.put("key", WeatherKey);
        String result = HttpUtil.net(url, params, "GET");
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
        // 请求参数
        Map<String, String> params = new HashMap<>();
        // 日期，格式2014-09-09
        params.put("date", DateUtil.format(new Date(), DateUtil.DATE_FORMAT_DAY));
        // 应用KEY(应用详细页查询)
        params.put("key", DayKey);
        String result = HttpUtil.net(url, params, "GET");
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
        // 请求参数
        Map<String, String> params = new HashMap<>();
        // 	星座名称，如:双鱼座
        params.put("consName", "天秤座");
        // 应用KEY(应用详细页查询)
        params.put("key", ConstellationKey);
        // 运势类型：today,tomorrow,week,month,year
        params.put("type", "today");
        String result = HttpUtil.net(url, params, "GET");
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
        // 请求参数
        Map<String, String> params = new HashMap<>();
        return HttpUtil.net(url, params, "GET");
    }

    @Override
    public JSONObject getHistory() throws Exception {
        // 请求接口地址
        String url = "https://api.oick.cn/lishi/api.php";
        // 请求参数
        Map<String, String> params = new HashMap<>();
        String result = HttpUtil.net(url, params, "GET");
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
        // 请求参数
        Map<String, Object> params = new HashMap<>();
        // 	目录编号
        int[] arr = {242, 252, 244, 248, 257, 243, 247, 251, 253, 250, 249, 245, 256, 254, 246, 255, 258};
        params.put("catalog_id", arr[(int) (Math.random() * arr.length)]);
        // 应用KEY(应用详细页查询)
        params.put("key", BookKey);
        // 数据返回起始
        params.put("pn", 1);
        // 数据返回条数，最大30
        params.put("rn", 1);
        String result = HttpUtil.net(url, params, "GET");
        JSONObject object = JSONObject.parseObject(result);
        if ((Integer) object.get("error_code") == 0) {
            return object;
        } else {
            log.error("从第三方api获取图书数据失败：{}", object.get("reason"));
            throw new BusinessException("获取图书数据失败！");
        }
    }
}
