package com.jyx.util.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * 时间操作类
 */
public abstract class UtilDate {

    /**
     * 返回当期时间，格式2010-12-20 15:36:28.171
     *
     * @param @return
     * @return Timestamp
     */
    public static Timestamp getNowTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前年信息
     *
     * @param @return
     * @return String
     */
    public static String getYear() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前年信息
     *
     * @param @return
     * @return String
     */
    public static String getYearYYYY() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取月信息
     *
     * @param @return
     * @return String
     */
    public static String getMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        return simpleDateFormat.format(new Date());
    }


    /**
     * 获取当期的天信息
     *
     * @param @return
     * @return String
     */
    public static String getDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前的时间
     *
     * @param @return
     * @return String
     */
    public static String getHour() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当期的时间的分钟
     *
     * @param @return
     * @return String
     */
    public static String getMinute() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前时间的秒信息
     *
     * @param @return
     * @return String
     */
    public static String getSecond() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前时间的毫秒信息
     *
     * @param @return
     * @return String
     */
    public static String getMillisecond() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("SSS");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 判断获取的日期，是否为当前日期
     *
     * @param @param  timestamp
     * @param @return
     * @return boolean
     */
    public static boolean isToday(Timestamp timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        return simpleDateFormat.format(timestamp).equals(simpleDateFormat.format(new Date()));
    }

    /**
     * 根据指定的格式，获取当期日期
     *
     * @param @param  dateformat
     * @param @return
     * @return String
     */
    public static String getCurrentDay(String dateformat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateformat);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取两个日期相隔的天数
     *
     * @param @param  firstDay
     * @param @param  secondDay
     * @param @return
     * @return long
     */
    public static long getDayBetween(String firstDay, String secondDay) {
        long dayBetween = 0L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date firstDate = simpleDateFormat.parse(firstDay);
            Date secondDate = simpleDateFormat.parse(secondDay);
            dayBetween = (firstDate.getTime() - secondDate.getTime()) / 0x5265c00L;
        } catch (ParseException e) {
            dayBetween = 0L;
            e.printStackTrace();
        }
        return dayBetween;
    }

    /**
     * 获取当前日期的时间戳
     */
    public static Timestamp getTodayTimestamp() {
        String currentDay = getCurrentDay("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestamp = null;
        try {
            timestamp = new Timestamp(simpleDateFormat.parse(currentDay).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 如果hours为正数那么把当前时间往后加多少个小时反之就是往前算
     *
     * @param format
     * @param hours
     * @return
     */
    public static String rollHoursTimestamp(String format, String hours) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String currentDayStr = getCurrentDay(format);
        Date currentDate = null;
        try {
            currentDate = simpleDateFormat.parse(currentDayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int hoursInt = UtilStr.getNotNullIntValue(hours, 0);
        Date result = new Date(currentDate.getTime() - (long) (hoursInt * 3600 * 1000));
        return simpleDateFormat.format(result).toString();
    }

    /**
     * amountDay为正数就是往后算间隔amountDay个天，如果为负数就是往前算间隔amountDay个天
     *
     * @param dateValue
     * @param amountDay
     * @return
     */
    public static Timestamp rollDayTimestamp(String dateValue, int amountDay) {
        String date = dateValue;
        if (date == null || date.trim().equals(""))
            date = getCurrentDay("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(5, amountDay);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        return timestamp;
    }

    /**
     * amountDay为正数就是往后算间隔amountDay个天，如果为负数就是往前算间隔amountDay个天
     *
     * @param dateValue
     * @param amountDay
     * @return
     */
    @SuppressWarnings("unused")
    public static Timestamp rollDayTimestamp(Timestamp timestampvalue, int amountDay) {

        if (timestampvalue == null)
            timestampvalue = getNowTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(timestampvalue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        calendar.add(5, amountDay);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        return timestamp;
    }

    /**
     * amoutMonth为正数就是往后算间隔amoutMonth个月，如果为负数就是往前算间隔amoutMonth个月
     *
     * @param date1
     * @param amoutMonth
     * @return
     */
    public static Timestamp rollMonthTimestamp(String date1, int amoutMonth) {
        String date = date1;
        if (date == null || date.trim().equals(""))
            date = getCurrentDay("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(2, amoutMonth);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        return timestamp;
    }

    /**
     * 根据指定的格式，格式化时间戳
     *
     * @param timestamp
     * @param format
     * @return String
     */
    public static String format(Timestamp timestamp, String format) {
        if (timestamp == null) {
            return null;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            String result = simpleDateFormat.format(timestamp);
            return result;
        }
    }

    /**
     * 根据指定的格式，格式化时间字符串
     *
     * @param time
     * @param format
     * @return String
     */
    public static String format(String time, String format) {
        if (UtilStr.isNull(time))
            return null;
        String time1 = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            time1 = simpleDateFormat.format(new Timestamp((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(time).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return time1;
    }

    /**
     * 根据阿拉伯数字翻译成月份，比如参数为1那么返回“一月” 如果为11，那么返回十一月
     *
     * @param month
     * @return
     */

    public static String getMonthStr(int month) {
        String monthInChinese = "";
        switch (month) {
            case 1: // '\001'
                monthInChinese = "\u4E00\u6708";
                break;

            case 2: // '\002'
                monthInChinese = "\u4E8C\u6708";
                break;

            case 3: // '\003'
                monthInChinese = "\u4E09\u6708";
                break;

            case 4: // '\004'
                monthInChinese = "\u56DB\u6708";
                break;

            case 5: // '\005'
                monthInChinese = "\u4E94\u6708";
                break;

            case 6: // '\006'
                monthInChinese = "\u516D\u6708";
                break;

            case 7: // '\007'
                monthInChinese = "\u4E03\u6708";
                break;

            case 8: // '\b'
                monthInChinese = "\u516B\u6708";
                break;

            case 9: // '\t'
                monthInChinese = "\u4E5D\u6708";
                break;

            case 10: // '\n'
                monthInChinese = "\u5341\u6708";
                break;

            case 11: // '\013'
                monthInChinese = "\u5341\u4E00\u6708";
                break;

            case 12: // '\f'
                monthInChinese = "\u5341\u4E8C\u6708";
                break;
        }
        return monthInChinese;
    }

    public static int getWeekOfYear() {
        int result = 0;
        Calendar calendar = Calendar.getInstance();
        result = calendar.get(3);
        return result;
    }

    public static String getStartDate(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        //YEAR = 1;
        calendar.set(1, year);
        //WEEK_OF_YEAR = 3;
        calendar.set(3, week);
        // DAY_OF_WEEK = 7
        calendar.set(7, 2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy\u5E74MM\u6708dd\u65E5");
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getEndDate(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(3, week + 1);
        calendar.set(7, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy\u5E74MM\u6708dd\u65E5");
        return simpleDateFormat.format(calendar.getTime());
    }

    public static Collection<Object> getTwoDateDayList(String bigDate, String smallDate) {
        Collection<Object> dayList = new ArrayList<Object>();
        long day = getDayBetween(bigDate, smallDate);
        dayList.add(smallDate);
        for (int i = 0; (long) i < day; i++) {
            Timestamp timestamp = rollDayTimestamp(smallDate, 1 + i);
            String endDate1 = format(timestamp, "yyyy-MM-dd");
            dayList.add(endDate1);
        }

        return dayList;
    }

    public static Collection<Object> getTwoDateDayListDesc(String bigDate, String smallDate) {
        Collection<Object> dayListDesc = new ArrayList<Object>();
        long days = getDayBetween(bigDate, smallDate);
        dayListDesc.add(bigDate);
        for (long i = days; i > 0L; i--) {
            Timestamp timestamp = rollDayTimestamp(bigDate, (int) (-1 - i));
            bigDate = format(timestamp, "yyyy-MM-dd");
            dayListDesc.add(bigDate);
        }

        return dayListDesc;
    }

    /**
     * 获取星期几。传入一个日期和该日期的类型，可以知道这个日期是星期几
     *
     * @param date
     * @param format
     * @return
     */
    public static String getWeekForDate(String date, String format) {
        String week = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date tempDate = null;
        try {
            tempDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("E");
        week = simpleDateFormat1.format(tempDate);
        return week;
    }

    /**
     * 根据传入的数字，获取标准的日期
     *
     * @param year
     * @param month
     * @param day
     * @return String
     */
    public static String getStandardDate(int year, int month, int day) {
        String yearString = String.valueOf(year);
        String monthString = String.valueOf(month);
        String dayString = String.valueOf(day);
        if (month < 10)
            monthString = (new StringBuilder("0")).append(month).toString();
        if (day < 10)
            dayString = (new StringBuilder("0")).append(day).toString();
        return (new StringBuilder(String.valueOf(yearString))).append("-").append(monthString).append("-").append(dayString).toString();
    }

    /**
     * 比当前时间小返回true，否则返回false
     *
     * @param comparedTime
     * @return
     */
    public static boolean compareTotime(String comparedTime) {
        boolean result = false;
        long dateMinusResult = 0L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = getCurrentDay("yyyy-MM-dd HH:mm:ss");
        try {
            Date currentDate = simpleDateFormat.parse(currentDateStr);
            Date comparedDate = simpleDateFormat.parse(comparedTime);
            dateMinusResult = currentDate.getTime() - comparedDate.getTime();
        } catch (ParseException e) {
            dateMinusResult = 0L;
            e.printStackTrace();
        }
        if (dateMinusResult > 0L)
            result = true;
        return result;
    }

    /**
     * 参数中bigTime 比smallTime大那么返回true否则返回false
     *
     * @param bigTime
     * @param smallTime
     * @return
     */
    public static boolean compareTotime(String bigTime, String smallTime) {
        boolean result = false;
        long temp = 0L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = simpleDateFormat.parse(bigTime);
            Date date2 = simpleDateFormat.parse(smallTime);
            temp = date1.getTime() - date2.getTime();
        } catch (ParseException e) {
            temp = 0L;
            e.printStackTrace();
        }
        if (temp > 0L)
            result = true;
        return result;
    }

    /**
     * 获取不为空的时间戳值
     *
     * @param timestamp
     * @param format
     * @return Timestamp
     */
    public static Timestamp getNotNullTimestampValue(String timestamp, String format) {
        Timestamp value;
        try {
            if (timestamp == null || timestamp.equals("")) {
                value = new Timestamp(System.currentTimeMillis());
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                value = new Timestamp(simpleDateFormat.parse(timestamp).getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
            value = new Timestamp(System.currentTimeMillis());
        }
        return value;
    }

    /**
     * 比较两个时间戳大小
     *
     * @param timestamp1
     * @param timestamp2
     * @param format
     * @return int
     */
    public static int compareTimstampStr(String timestamp1, String timestamp2, String format) {
        int result = 10;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long temp = 10L;
        try {
            Date date1 = simpleDateFormat.parse(timestamp1);
            Date date2 = simpleDateFormat.parse(timestamp2);
            temp = date1.getTime() - date2.getTime();
        } catch (ParseException e) {
            result = 0x7fffffff;
            e.printStackTrace();
        }
        if (temp > 0L)
            result = 1;
        else if (temp == 0L)
            result = 0;
        else if (temp < 0L)
            result = -1;
        return result;
    }

    /**
     * 根据指定的格式，格式化时间
     *
     * @param pattern
     * @param date
     * @return String
     */
    public static String formatDate(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        if (null == sdf || null == date) {
            return "";
        }
        return sdf.format(date);
    }

    /**
     * 格式化日期
     *
     * @param dateStr
     * @return Date
     */
    public static Date parseDateFrom(String dateStr) {
        if (null == dateStr) {
            return new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date value = null;
        try {
            value = sdf.parse(dateStr);
        } catch (ParseException ex) {
        }
        return value;
    }

    /**
     * 根据当前时间，取其上两个月的月初时间，返回格式：“2010-01-01”
     * 如当前日期为“2010-09-29”，则返回为“2010-07-01”
     *
     * @return
     */
    public static String getDateBeforeThreeMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        String year = today.substring(0, 4);
        String month = today.substring(5, 7);

        String theDate = "";
        if (Integer.parseInt(month) < 3) {
            if ("02".equals(month) || "2".equals(month)) {
                theDate = (Integer.parseInt(year) - 1) + "-12-01";
            } else if ("01".equals(month) || "1".equals(month)) {
                theDate = (Integer.parseInt(year) - 1) + "-11-01";
            }
        } else {
            if ((Integer.parseInt(month) - 2) < 10) {
                theDate = year + "-0" + (Integer.parseInt(month) - 2) + "-01";
            } else {
                theDate = year + "-" + (Integer.parseInt(month) - 2) + "-01";
            }
        }
        return theDate;
    }
}