package com.hope.blog;


import com.hope.blog.utils.IPUtil;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@Ignore
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
}


