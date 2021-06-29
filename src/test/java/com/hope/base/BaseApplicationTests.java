package com.hope.base;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.hope.blog.utils.IPUtil;
import com.hope.blog.utils.RedisUtil;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

@SpringBootTest
@Ignore
class BaseApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {

    }

    @Test
    void TestDateUtils() {
        String ip = "220.248.12.158";
        String result = IPUtil.getCityInfo(ip);
        System.out.println(result);
    }

    @Test
    public void testHttpUtil() {
        redisUtil.set("0","1");
    }

    @Test
    public void sum() {
        System.out.println(sum(5));
    }


    @Test
    public void testFastJson() {
        int page =0;
        do {
            ++page;
            System.out.println(page);
        } while (page != 5);
    }

    @Test
    public void testQrcode() {
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("d:/qrcode.jpg"));
    }

    @Test
    public void testPasswordEncoder() {
        boolean matches = passwordEncoder.matches("123456", "$2a$10$rZT6UZrwD8VVlvheo6UcNechUMMsjVqq9x.7c6cSYa/RWNnBp08z2");
        System.out.println(matches);
    }

    @Test
    public void reg(){
        String content = "I am noob " + "from runoob.com.";

        String pattern = ".*runoob.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);;
    }

    @Test
    public void testDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beginTime = Calendar.getInstance();
        beginTime.add(Calendar.MINUTE,-5);
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.MINUTE,0);

        System.out.println(beginTime.getTime());
        System.out.println(endTime.getTime());

    }

    public int sum(int n) {
        if (n <= 1) {
            return 1;
        }
        return sum(n - 1) + n;
    }
}


