package com.jyx.util.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 判断操作系统操作类
 */
public class UtilOS {

    private static final String WINDOWS = "WINDOWS";
    protected static final Logger logger = LoggerFactory.getLogger(UtilOS.class);
    private static final double FREE_TOTALL_PERCENT = 0.05;

    /**
     * 判断当前操作系统的类型
     *
     * @return false means window system ,true means unix like system
     */
    public static boolean isUnixLikeSystem() {
        String name = System.getProperty("os.name");
        if (name != null) {
            if (name.toUpperCase().indexOf(WINDOWS) == -1) { //it means it's unix like system
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前JVM可用内存大小
     *
     * @return
     */
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 检测JVN当前是否会内存泄露
     *
     * @return
     */
    public static boolean checkJVMHealth() {
//		long freeSize = getFreeMemory();
        double freeMem = Runtime.getRuntime().freeMemory();
        logger.debug("free memory size is :" + freeMem);
        double totlMem = Runtime.getRuntime().totalMemory();
        logger.debug("total memory size is : " + totlMem);
        double percent = freeMem / totlMem;
        logger.debug("percent is " + percent);
        if (percent <= FREE_TOTALL_PERCENT) {
            return true;
        }
        return false;
    }

//	public static void main(String[] args)
//	{
//		System.out.println("total size is :" + Runtime.getRuntime().totalMemory());
//		System.out.println("max mem (meauser in bytes)" + Runtime.getRuntime().maxMemory());
//		System.out.println("current mem size is : " + UtilOS.getFreeMemory());
//	}
}
