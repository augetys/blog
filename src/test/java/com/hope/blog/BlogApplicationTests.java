package com.hope.blog;

import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.service.BlogArticleService;
import com.hope.blog.utils.MailUtil;
import com.hope.blog.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootTest
@Ignore
@Slf4j
class BlogApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void tests(){
        String s="中国|云南|昆明|电信";
        System.out.println(s.split("\\|")[2]);
    }

    @Test
    public void setMail() {
        MailUtil.send("激活账号", "hahahahha", "1181881941@qq.com");
    }

    @Test
    public void getEnv() {
        String activeProfile = SpringContextHolder.getActiveProfile();
        System.out.println(activeProfile);
    }


    @Autowired
    private BlogArticleService blogArticleService;

    @Test
    public void randomDate() throws Exception {
        List<BlogArticle> list = blogArticleService.list();
        for (BlogArticle blogArticle : list) {
            blogArticle.setCreateTime(randomDate("2020-03-08", "2022-03-08"));
        }
        blogArticleService.saveOrUpdateBatch(list);
    }

    public static Date randomDate(String beginDate, String endDate) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}


