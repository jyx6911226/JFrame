package com.jyx.util.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件操作类
 */
public abstract class UtilFile {

    /**
     * 判断文件夹是否存在
     *
     * @param path —— 文件路径
     *             true means exist or not
     */
    public static boolean judgeIfDirecotryExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 创建文件夹
     *
     * @param path —— 文件路径
     *             true means create successfully or fail
     */
    public static boolean createDirectory(String path) {
        File file = new File(path);
        return file.mkdirs();
    }

    /**
     * 检查文件夹是否存在，如果不存在就创建
     *
     * @param path —— 文件路径
     *             返回 true 表示创建成功，否则false创建失败
     */
    public static boolean createDirectoryIfNotExist(String path) {
        if (judgeIfDirecotryExist(path) == false) {
            return createDirectory(path);
        }
        return true;
    }

    /**
     * 保存文件到磁盘上
     *
     * @param upload         —— 上传文件
     * @param uploadFileName —— 上传的文件名称
     * @param path           —— 文件路径
     */
    @SuppressWarnings("unused")
    public static Map<String, File> saveFilesOnDisk(File[] upload, String[] uploadFileName, String path) throws FileNotFoundException, IOException {

        if (upload == null) {
            return null;
        }
        createDirectoryIfNotExist(path);
        Map<String, File> files = new HashMap<String, File>(upload.length);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        for (int i = 0; i < upload.length; i++) {
            try {
                fis = new FileInputStream(upload[i]);
                String physicName = getPhysicName();
                String appendPattern = getPostfix(uploadFileName[i]);
                String filePath = path + physicName + appendPattern;
                fos = new FileOutputStream(filePath);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fis.read(buffer)) != -1) {
                    fos.write(buffer);
                }
                fos.flush();
                File destFile = new File(filePath);

                files.put(uploadFileName[i], destFile);
            } finally {
                fis.close();
                //if use fos.flush() we dont need use fos.close() ? I am not sure
                fos.close();
            }
        }
        return files;
    }

    /**
     * 以时间的格式返回文件的物理名称
     *
     * @return
     */
    private static String getPhysicName() {
        return String.valueOf(UtilDate.getNowTime().getTime());
    }

    /**
     * 获取postfix 的名称
     *
     * @param fileName
     * @return
     */
    public static String getPostfix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 获取附件的大小
     *
     * @param attachFiles —— 附件列表
     */
    public static long caculateSize(List<File> attachFiles) {
        if (attachFiles == null) {
            return 0;
        }
        long size = 0;
        for (File attachFile : attachFiles) {
            size += attachFile.length();
        }
        return size;
    }

    /**
     * 获取文件大小
     *
     * @param attachFiles —— 文件列表
     */
    public static long caculateSize(File[] attachFiles) {
        return caculateSize(Arrays.asList(attachFiles));
    }

    /**
     * 获取文件大小
     *
     * @param attachFiles —— 文件
     */
    public static long caculateSize(File file) {
        return file.length();
    }

    /**
     * 获取流对象中的文件大小
     *
     * @param is —— 输入流对象
     */
    public static long caculateSize(InputStream is) throws IOException {
        int read = 0;
        long size = 0;
        byte[] buffer = new byte[100];
        while ((read = is.read(buffer)) != -1) {
            size = read + size;
        }
        return is.available();
    }

    /**
     * 获取文件的物理名称
     *
     * @param realName ——真实名称
     * @return
     */
    public static String getPhysicFileName(String realName) {
        String physiceFileName = getPhysicName();
        String physicName = physiceFileName + getPostfix(realName);
        return physicName;
    }

    /**
     * 保存文件到磁盘
     *
     * @param is         —— 流对象
     * @param path       —— 文件路径
     * @param physicName ——物理名称
     * @param realName   ——真实名称
     * @return
     * @throws IOException
     */
    public static Map<String, File> saveFilesOnDiskFrom(InputStream is, String path, String physicName, String realName) throws IOException {
        String filePath = saveFileOnDisk(is, path, realName);
        File file = new File(filePath);
        Map<String, File> rsltMap = new HashMap<String, File>();
        rsltMap.put(realName, file);
        return rsltMap;
    }

    public static String saveFileOnDisk(InputStream is, String path, String realFileName) throws FileNotFoundException, IOException {
        createDirectoryIfNotExist(path);
        String filePath = path + getPhysicName() + getPostfix(realFileName);
        FileOutputStream fos = new FileOutputStream(filePath);

        byte[] buffer = new byte[100];
        int read = 0, size = 0;
        try {
            while ((read = is.read(buffer)) != -1) {
                size = read + size;
                fos.write(buffer);
            }
            fos.flush();
        } finally {
            fos.close();
        }
        return filePath;
    }
}
