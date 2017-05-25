package com.jyx.util.utils;

/**
 * 随即数操作类
 */
public abstract class UtilRandom {
    /**
     * @param min
     * @param max
     * @return int
     */
    public static int randomInt(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }

    /**
     * @param min
     * @param max
     * @return double
     */
    public static double randomDouble(double min, double max) {
        return (min + Math.random() * (max - min));
    }

    public static long randomLong(long min, long max) {
        return (min + (long) (Math.random() * (max - min)));
    }

    /**
     * 生成5位随机数
     *
     * @return
     */
    public final static String get5Radom() {
        String newString = null;

        //得到0.0到1.0之间的数字,并扩大100000倍  
        double doubleP = Math.random() * 100000;

        //如果数据等于100000,则减少1  
        if (doubleP >= 100000) {
            doubleP = 99999;
        }

        //然后把这个数字转化为不包含小数点的整数  
        int tempString = (int) Math.ceil(doubleP);

        //转化为字符串  
        newString = "" + tempString;

        //把得到的数增加为固定长度,为5位  
        while (newString.length() < 5) {
            newString = "0" + newString;
        }

        return newString;
    }
}
