package com.hope.blog.utils;

import com.hope.blog.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Server工具类
 * Created by lijin on  2021/4/2
 */
@Slf4j
public class DateUtil {

    /**
     * 日期格式 年 如2009
     */
    public static final String DATE_FORMAT_YEAR = "yyyy";

    /**
     * 日期格式 年 月  如 2009-02
     */
    public static final String DATE_FORMAT_MONTH = "yyyy-MM";

    /**
     * 日期格式 年 月 日 如2009-02-26
     */
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";

    /**
     * 日期格式年 月 日 时 分 秒 如 2009-02-26 15:40:00
     */
    public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式年 月 日 时 分 秒 毫秒 如2009-02-26 15:40:00 110
     */
    public static final String DATE_FORMAT_MILLI_SECOND = "yyyy-MM-dd HH:mm:ss SSS";


    /**
     * 将  String 转换成  date
     *
     * @param dateTime
     * @return
     */
    public static Date parse(String dateTime, String format) throws ParseException {
        if (StringUtils.isEmpty(dateTime)) {
            return null;
        }
        return new SimpleDateFormat(format).parse(dateTime);
    }


    /**
     * Date 转换成  String
     *
     * @param dateTime，formatDate
     * @return
     */
    public static String format(Date dateTime, String formatDate) {
        if (dateTime == null) {
            return null;
        }
        return new SimpleDateFormat(formatDate).format(dateTime);
    }


    /**
     * 获取现在的时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_SECOND);
        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 将  date 转换成  时间戳
     *
     * @return
     */
    public static Long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_SECOND);
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }

    /**
     * 获取某个时间段内所有日期
     *
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getDayBetweenDates(String begin, String end) throws ParseException {
        Date dBegin = parse(begin, DATE_FORMAT_SECOND);
        Date dEnd = parse(end, DATE_FORMAT_SECOND);
        List<String> lDate = new ArrayList<>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 得到二个日期间的间隔天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDayByTwoDay(String date1, String date2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0L;
        try {
            java.util.Date date = myFormatter.parse(date1);
            java.util.Date mydate = myFormatter.parse(date2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return 0;
        }
        return (int) day;
    }

    /**
     * 得到两个日期相差的秒数
     *
     * @param lastDate
     * @param date
     * @return
     */
    public static int getSecondByTwoDay(Date lastDate, Date date) {
        long second = 0L;
        try {
            second = (lastDate.getTime() - date.getTime()) / 1000;
        } catch (Exception e) {
            return 0;
        }
        return (int) second;
    }

    /**
     * 判断某个日期属于本月的第几天
     *
     * @param dateTime
     * @return
     * @throws ParseException
     */
    public static int getDaysByMonth(String dateTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateTime);
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }


    /**
     * 获取当前的年
     *
     * @return
     */
    public static Integer getYears() {
        Calendar calendar = new GregorianCalendar(TimeZone
                .getDefault());
        return calendar.get(Calendar.YEAR);

    }

    /**
     * 获取当前的月
     *
     * @return
     */
    public static Integer getMonth() {
        Calendar calendar = new GregorianCalendar(TimeZone
                .getDefault());
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前天
     *
     * @return
     */
    public static Integer getDay() {
        Calendar calendar = new GregorianCalendar(TimeZone
                .getDefault());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前日期与本周日相差的天数
     *
     * @return
     */
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        // 因为按中国礼拜一作为第一天所以这里减1
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }


    /**
     * 获取几天之后的日期
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @param day  加减的天数
     * @return
     */
    public static Date getDate(String date, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            Date beforeDate = format.parse(date);
            cal.setTime(beforeDate);
            cal.add(Calendar.DAY_OF_MONTH, day);
            return cal.getTime();
        } catch (ParseException e) {
          throw new BusinessException("日期格式化异常:",e);
        }
    }

    /**
     * 获取过去N天内的日期数组
     *
     * @param intervals intervals天内
     * @param formatStr 格式化字符串   yyyy-MM-dd
     * @return 日期数组
     */
    public static ArrayList<String> getDaysByN(int intervals, String formatStr) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals - 1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i, formatStr));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past, String formatStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(today);
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个日期时间差
     *
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取上个月的同一天
     * 像有些月的前一个月是没有31号的，则默认用这个月的31号去与上个月的30;另外2月份也是一个特殊的月，闰年时为29天，非闰年为28天，所以闰年的3月份的30，31的上个月为29号，非闰年的29，30，31号的上一个月的同一天为28号。还有就是这个月是1月，这上个月是去年的12月。
     *
     * @param date
     * @return
     */
    public Date getBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long millis = getBefore(calendar);
        calendar.setTimeInMillis(millis);

        return calendar.getTime();
    }

    private long getBefore(Calendar c) {
        int month = (c.get(Calendar.MONTH) + 1);
        long monthMillis = 0;
        switch (month) {
            // 31 day
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                monthMillis = calculateMillis(31);
                break;
            // 30 day
            case 4:
            case 6:
            case 9:
            case 11:
                monthMillis = calculateMillis(30);
                break;
            // 2 month
            default:
                if (isLeapYear(c)) {
                    monthMillis = calculateMillis(29);
                } else {
                    monthMillis = calculateMillis(28);
                }
                break;
        }
        System.out.println(monthMillis);
        return (c.getTimeInMillis() - monthMillis);
    }

    private long calculateMillis(int month) {
        return month * (long) 24 * 60 * 60 * 1000;
    }

    private boolean isLeapYear(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }
}
