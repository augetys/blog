package com.hope.blog;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailUtil;
import com.hope.blog.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@SpringBootTest
@Ignore
@Slf4j
class BaseApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void TestDateUtils() {
        String ip = "61.138.196.204";
        String result = IPUtil.getCityInfo(ip);
        System.out.println(result);
    }

    @Test
    void Test01(){
        Calendar time=Calendar.getInstance();
        time.setTime(new Date());
        time.add(Calendar.YEAR,-1);
        System.out.println(time.getTime());
    }
}


