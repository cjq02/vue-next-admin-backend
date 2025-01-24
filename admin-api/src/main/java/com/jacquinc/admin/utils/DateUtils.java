package com.jacquinc.admin.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuxinzh
 * created on  2019/3/4
 */
public class DateUtils extends com.jiujie.framework.base.utils.DateUtils {

    private DateUtils() {
    }

    public static final String FORMAT_SHORT = "yyyy-MM-dd";
    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 根据时间和格式返回字符串
     *
     * @param uDate
     * @param pattern
     * @return
     */
    public static String dateToString(Date uDate, String pattern) {
        return new SimpleDateFormat(pattern).format(uDate);
    }

    /**
     * 将时间转成yyyy-MM-dd格式字符串
     *
     * @param uDate
     * @return
     */
    public static String dateToString(Date uDate) {
        return dateToString(uDate, FORMAT_SHORT);
    }

    /**
     * 将时间转成yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param uDate
     * @return
     */
    public static String timeToString(Date uDate) {
        return dateToString(uDate, FORMAT_LONG);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentTime() {
        return new Date();
    }

    /**
     * 获取几个月以前
     *
     * @return 年月
     */
    public static String getYearMonthByCurrentSubNumber(Date date, int number) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, -number);
        return format.format(cal.getTime());
    }

    /**
     * 获取当前日期字符串
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return dateToString(getCurrentTime());
    }

    public static Date getCurrentDateInLastYear() {
        Calendar prevYear = Calendar.getInstance();
        prevYear.add(Calendar.YEAR, -1);
        return prevYear.getTime();
    }

    /**
     * 获取当前时间字符串
     *
     * @return
     */
    public static String getCurrentTimeStr() {
        return timeToString(getCurrentTime());
    }

    /**
     * 按时间字符串，将时间加N天后，返回新的时间字符串，格式yyyy-MM-dd
     *
     * @param beginDateStr
     * @param day
     * @return
     */
    public static String addDay(String beginDateStr, int day) throws ParseException {
        if (StringUtils.isBlank(beginDateStr)) {
            return null;
        }
        return addDay(beginDateStr, FORMAT_SHORT, day);
    }

    /**
     * 按时间字符串，将时间加N天后，返回新的时间字符串
     *
     * @param beginDateStr
     * @param pattern
     * @param day
     * @return
     */
    public static String addDay(String beginDateStr, String pattern, int day) throws ParseException {
        if (StringUtils.isBlank(beginDateStr) || StringUtils.isBlank(pattern)) {
            return null;
        }
        Date beginDate = stringToDate(beginDateStr, pattern);
        Date endDate = addDay(beginDate, day);
        return dateToString(endDate, pattern);
    }

    /**
     * 将字符串按格式转成时间
     *
     * @param strDate
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strDate, String pattern)
            throws ParseException {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            return null;
        }
        return new SimpleDateFormat(pattern).parse(strDate);
    }

    /**
     * 将字符串按yyyy-MM-dd转成时间
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strDate) throws ParseException {
        return stringToDate(strDate, FORMAT_SHORT);
    }

    /**
     * 将日期增加几天
     *
     * @param beginDate
     * @param day
     * @return
     */
    public static Date addDay(Date beginDate, int day) {
        if (beginDate == null) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(beginDate);
        ca.add(Calendar.DATE, day);
        return ca.getTime();
    }

    public static Date subDay(Date beginDate, int day) {
        if (beginDate == null) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(beginDate);
        ca.add(Calendar.DATE, -day);
        return ca.getTime();
    }

    public static int getDaysOfMonth(String year, String month) throws ParseException {
        String dateStr = year + "-" + month + "-01";
        Date date = stringToDate(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param minDate 最小时间  2015-01
     * @param maxDate 最大时间 2015-10
     * @return 日期集合 格式为 年-月
     * @throws Exception
     */
    public static List<Map<String, Object>> getMonthBetween(String minDate, String maxDate) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        //格式化为年月
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        while (min.before(max)) {
            Map<String, Object> map = new HashMap<>();
            String yearMonth = sdf.format(min.getTime());
            map.put("year", yearMonth.split("-")[0]);
            map.put("month", yearMonth.split("-")[1]);
            result.add(map);
            min.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * @param minDate 最小时间  2015-01
     * @param maxDate 最大时间 2015-10
     * @return 日期集合 格式为 年-月
     * @throws Exception
     */
    public static List<String> getYearMonthListBetween(String minDate, String maxDate) throws Exception {
        List<String> result = new ArrayList<>();
        //格式化为年月
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        while (min.before(max)) {
            String yearMonth = sdf.format(min.getTime());
            result.add(yearMonth);
            min.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * 获取最近三年数组
     *
     * @return
     */
    public static List<String> recentlyThreeYears() {
        List<String> years = new ArrayList<>();
        Integer currYear = Calendar.getInstance().get(Calendar.YEAR);
        years.add(String.valueOf(currYear - 1));
        years.add(String.valueOf(currYear - 2));
        years.add(String.valueOf(currYear - 3));
        return years;
    }

    public static List<String> getYearMonthListByLength(String yearMonth, int length) throws Exception {
        Date currentDate = DateUtils.stringToDate(yearMonth, "yyyy-MM");
        String startYearMonth = DateUtils.getYearMonthByCurrentSubNumber(currentDate, length);
        String endYearMonth = DateUtils.getCurrentYearMonthDate();
        DateUtils.getMonthBetween(startYearMonth, endYearMonth);
        return new ArrayList<>(DateUtils.getYearMonthListBetween(startYearMonth, endYearMonth));
    }

    /**
     * 获取年月的最后一天
     *
     * @param yearMonth 年月
     * @return 日期
     */
    public static Date getFirstDateInYearMonth(String yearMonth) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate localDate = YearMonth.parse(yearMonth, pattern).atDay(1);
        return java.sql.Date.valueOf(localDate);
    }

    /**
     * 获取年月的最后一天
     *
     * @param yearMonth 年月
     * @return 日期
     */
    public static Date getLastDateInYearMonth(String yearMonth) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate localDate = YearMonth.parse(yearMonth, pattern).atEndOfMonth();
        return java.sql.Date.valueOf(localDate);
    }

    public static Date getHalfHourAgoDate() {
        return getDateByPastMinutes(30);
    }

    public static Date getDateByPastMinutes(Integer minutes) {
        return Date.from(Instant.now().minus(Duration.ofMinutes(minutes)));
    }

    public static long getOffSeconds(Date from, Date to) {
        return (to.getTime() - from.getTime()) / 1000;
    }

    public static String addMinutes(String dateStr, int minutes) {
        Date date = DateUtils.getString2LongDate(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return DateUtils.getDate2LongString(calendar.getTime());
    }

    public static Date addMinutes(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, amount);
        return c.getTime();
    }
}
