package com.jyx.util.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具类
 */
public abstract class UtilStr {
    private static Logger logger = LoggerFactory.getLogger(UtilStr.class);

    /**
     * 判断是否为空
     *
     * @param value
     * @return boolean
     */
    public static boolean isNull(String value) {
        return (value == null) || value.trim().isEmpty();
    }

    /**
     * 判断是否不为空
     *
     * @param value
     * @return boolean
     */
    public static boolean isNotNull(String value) {
        return (value != null) && !value.trim().isEmpty();
    }

    /**
     * 获取字符串的整数值
     *
     * @param value
     * @return int
     */
    public static int getNotNullIntValue(String value) {
        int result = 0;

        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            result = 0;
            logger.debug("parse error", e);
        }

        return result;
    }

    /**
     * 获取字符串的整数值
     *
     * @param value
     * @param defaultValue —— 字符串为空时，返回的默认值
     * @return int
     */
    public static int getNotNullIntValue(String value, int defaultValue) {
        int result = getNotNullIntValue(value);

        if (result == 0) {
            result = defaultValue;
        }

        return result;
    }

    /**
     * 判断是否为中文
     *
     * @param value
     * @return boolean
     */
    public static boolean isChinese(String value) {
        boolean result = false;
        String reg = "[^u4E00-u9FA5]+";
        Pattern pattern = Pattern.compile(reg, 34);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            result = true;
        }

        return result;
    }

    /**
     * 分割字符串
     *
     * @param target
     * @param seperator
     * @return String[]
     */
    public static String[] splitBy(String target, String seperator) {
        if (null == target) {
            return null;
        }

        String rsltStr[] = target.split(seperator);

        return rsltStr;
    }

    /**
     * 拼接字符串
     *
     * @param target    —— 字符串类表对象
     * @param seperator —— 拼接时的分割符
     * @return String
     */
    public static String mergerBy(List<String> target, String seperator) {
        return mergerBy(convertListToArray(target), seperator);
    }

    /**
     * 拼接字符串
     *
     * @param target    —— 字符串
     * @param seperator —— 拼接时的分割符
     * @return String
     */
    public static String mergerBy(String[] target, String seperator) {
        if (null == target) {
            return null;
        }

        StringBuilder sb = new StringBuilder(256);

        for (String str : target) {
            sb.append(str);
            sb.append(seperator);
        }

        return sb.substring(0, sb.lastIndexOf(seperator));
    }

    /**
     * 将字符串存入List对象中
     *
     * @param list
     * @param str
     * @return List<String>
     */
    public static List<String> addStrTo(List<String> list, String str) {
        if (null == list) {
            list = new ArrayList<String>();
        }

        list.add(str);

        return list;
    }

    /**
     * 将数组转换为List对象
     *
     * @param list —— 列表对象
     * @param strs ——数组
     * @return List<String>
     */
    public static List<String> convertArrayToList(List<String> list, String[] strs) {
        if (null == strs) {
            return new ArrayList<String>(50);
        }

        if (null == list) {
            list = new ArrayList<String>(50);
        }
        list.addAll(Arrays.asList(strs));
        return list;
    }

    /**
     * 将list对象转换为数组
     *
     * @param list
     * @return String[]
     */
    public static String[] convertListToArray(List<String> list) {
        return list.toArray(new String[0]);
    }

    /**
     * 判断对象内容是否为空
     *
     * @param htmlSB
     * @return boolean
     */
    public static boolean hasContent(StringBuilder htmlSB) {
        if ((null == htmlSB.toString()) || "".equalsIgnoreCase(htmlSB.toString())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 解压缩代码
     *
     * @param gzipped
     * @param estimateSize
     * @return
     */
    public static byte[] ungzip(final byte[] gzipped, int estimateSize) {
        byte[] ungzipped = new byte[0];

        try {
            final GZIPInputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(gzipped));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(gzipped.length);
            final byte[] buffer = new byte[estimateSize];
            int bytesRead = 0;

            while (bytesRead != -1) {
                bytesRead = inputStream.read(buffer, 0, estimateSize);

                if (bytesRead != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
            }

            ungzipped = byteArrayOutputStream.toByteArray();
            inputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            logger.error("error : ", e);
        }

        return ungzipped;
    }

    /**
     * 压缩代码
     *
     * @param ungzipped
     * @return byte[]
     */
    public static byte[] gzip(byte[] ungzipped) {
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        try {
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bytes);

            gzipOutputStream.write(ungzipped);
            gzipOutputStream.close();
        } catch (IOException e) {
            logger.error("error : ", e);
        }
        return bytes.toByteArray();
    }
}
/*
 * @(#)StrUtil.java   2010.11.02 at 04:46:43 CST
 *
 * Copyright (c) 2005 blzccn.com
 *
 */
//~ Formatted by Jindent --- http://www.jindent.com

