package com.yc.pratice.office.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getDate(String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        return simple.format(new Date());
    }

    public static String getDateAndMinute() {
        SimpleDateFormat simple = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return simple.format(new Date());
    }

    public static String getFullDate() {
        SimpleDateFormat simple = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return simple.format(new Date());
    }

    public static String getVersion() {
        String date = getFullDate();
        String version = date.replaceAll("-", "");
        version = version.replaceAll(":", "");
        version = version.replaceAll(" ", "");
        return version;
    }

    // 对版本号排序
    public static List<String> sortVersions(Set<String> set) {
        List<String> list = new ArrayList<>();
        list.addAll(set);
        Collections.sort(list);
        return list;
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            try {
                return sdf.format(date);
            } catch (Exception e) {
                logger.error("转换Date: {0}到字符串异常：{1}。", date, e);
            }
        }
        return "";
    }

    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取距离下一个整点的毫秒差值
     *
     * @return
     */
    public static long getNextHourMs() {
        Calendar calc = Calendar.getInstance();
        int hour = calc.get(Calendar.HOUR_OF_DAY);
        calc.set(Calendar.HOUR_OF_DAY, hour + 1);
        calc.set(Calendar.MINUTE, 0);
        calc.set(Calendar.SECOND, 0);
        Date date = calc.getTime();
        return date.getTime() - System.currentTimeMillis();
    }

    public static long getNextMinMs() {
        Calendar calc = Calendar.getInstance();
        int min = calc.get(Calendar.MINUTE);
        calc.set(Calendar.MINUTE, min + 1);
        calc.set(Calendar.SECOND, 0);
        Date date = calc.getTime();
        return date.getTime() - System.currentTimeMillis();
    }

    public static Date stringToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        if (!EmptyUtil.isEmpty(dateStr)) {
            try {
                return sdf.parse(dateStr);
            } catch (Exception e) {
                logger.error("转换字符串: {}到Date异常：{}。", dateStr, e);
                return new Date();
            }
        }
        return null;
    }

    public static Date getCurrDate() {
        return new Date();
    }

    /**
     * 获取当前几号
     *
     * @return
     */
    public static Integer getCurrentDay() {
        Calendar cale = Calendar.getInstance();
        return cale.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取本月第一天
     *
     * @return
     */
    public static Date getCurrMonthFirstDay() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @return
     */
    public static Date getCurrMonthLastDay() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    public static Date getMonthFirstDay(Integer month) {
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.MONTH, month - 1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.add(Calendar.DATE, 0);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        return cale.getTime();
    }

    /**
     * 获取前一个月第一天
     *
     * @return
     */
    public static Date getPreMonthFirstDay() {
        Calendar cale1 = Calendar.getInstance();
        cale1.add(Calendar.MONTH, -1);
        cale1.set(Calendar.DAY_OF_MONTH, 1);
        return cale1.getTime();
    }

    /**
     * 获取当天剩余的毫秒数
     *
     * @return
     */
    public static long getTodayLastMs() {
        long currentMs = System.currentTimeMillis();
        Date nextZeroDate = DateUtil.getNextZeroTime();
        return nextZeroDate.getTime() - currentMs;
    }

    /**
     * 获取当天剩余的毫秒数，根据传入的秒数进行相减，获取到今晚n点的剩余毫秒值
     *
     * @return
     */
    public static long getTodayLastMs(long decreaseSec) {
        long currentMs = System.currentTimeMillis();
        Date nextZeroDate = DateUtil.getNextZeroTime();
        return nextZeroDate.getTime() - currentMs - (decreaseSec * 1000);
    }

    /**
     * 获取下一天的零点时间
     *
     * @return
     */
    public static Date getNextZeroTime() {
        Calendar calc = Calendar.getInstance();
        calc.add(Calendar.DATE, 1);
        calc.set(Calendar.HOUR_OF_DAY, 0);
        calc.set(Calendar.MINUTE, 0);
        calc.set(Calendar.SECOND, 0);
        return calc.getTime();
    }

    public static Integer getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static Integer getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取前一个月第一天
     *
     * @return
     */
    public static Date getPreMonthLastDay() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     * 获取前一年当前月的第一天
     *
     * @return
     */
    public static Date getPreYearCurrMonthFirstDay() {
        Calendar cale2 = Calendar.getInstance();
        cale2.add(Calendar.YEAR, -1);
        cale2.set(Calendar.DAY_OF_MONTH, 1);
        return cale2.getTime();
    }

    /**
     * 获取前一年当前月的最后一天
     *
     * @return
     */
    public static Date getPreYearCurrMonthLastDay() {
        Calendar cale2 = Calendar.getInstance();
        cale2.add(Calendar.YEAR, -1);
        cale2.add(Calendar.MONTH, 1);
        cale2.set(Calendar.DAY_OF_MONTH, 0);
        return cale2.getTime();
    }

    /**
     * 获取昨天0点
     *
     * @return
     */
    public static Date getYesterdayZero() {
        Calendar cale2 = Calendar.getInstance();
        cale2.add(Calendar.DATE, -1);
        cale2.set(Calendar.HOUR_OF_DAY, 0);
        cale2.set(Calendar.MINUTE, 0);
        cale2.set(Calendar.SECOND, 0);
        return cale2.getTime();
    }

    /**
     * 获取昨天同一时刻
     *
     * @return
     */
    public static Date getYesterday() {
        Calendar cale2 = Calendar.getInstance();
        cale2.add(Calendar.DATE, -1);
        return cale2.getTime();
    }

    /**
     * 获取昨天0点
     *
     * @return
     */
    public static Date getTodayZero() {
        Calendar cale2 = Calendar.getInstance();
        cale2.set(Calendar.HOUR_OF_DAY, 0);
        cale2.set(Calendar.MINUTE, 0);
        cale2.set(Calendar.SECOND, 0);
        return cale2.getTime();
    }
}
