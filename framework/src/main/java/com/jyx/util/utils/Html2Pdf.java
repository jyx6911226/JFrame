package com.jyx.util.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * HTML转换成pdf工具类
 * 原理及安装说明：
 * 1.在http://wkhtmltopdf.org/downloads.html下载对应操作系统的wkhtmltopdf并安装
 * 2.以windows为例wkhtmltopdf转换pdf的程序为wkhtmltopdf.exe，
 * 其转换命令为：wkhtmltopdf.exe htmlFilePath pdfsavePath
 * <p>
 * wkhtmltopdf与itext比较：纠错能力强，支持中文，显示效果好，但需要在服务端安装程序。
 *
 * @author jyx
 */
public class Html2Pdf {
    //	public static void main(String[] args) {
//		File htmlFile = new File("C:/Users/Administrator/Desktop/view.html");
//		try {
//			String classPath = Class.forName("cn.wiztek.rebp.org.utils.html2pdf.Html2Pdf").getResource("/").getPath();
//			String webRoot = new File(classPath).getParentFile().getParent();// WEB-Root 目录的物理路径
//			String imgPath = "/PDFFiles/"+UUID.randomUUID()+".pdf";
//			String savePath = webRoot+imgPath;
//			Html2Pdf html2Pdf = new Html2Pdf();
//			html2Pdf.html2Pdf(htmlFile, savePath);
//			System.out.println("生成pdf成功");
//		} catch (ClassNotFoundException e) {
//			System.out.println("生成pdf失败");
//			e.printStackTrace();
//		}
//	}
    private static final String HTML2PDF_PATH = "D:\\wkhtmltopdf\\bin\\";

    /**
     * 调用HTML2PDF插件将HTML转换为PDF
     *
     * @param htmlFile html文件
     * @param savePath pdf保存路径
     */
    public void html2Pdf(File htmlFile, String savePath) {
        String commandStr = HTML2PDF_PATH + "wkhtmltopdf.exe " + htmlFile + " " + savePath;
        System.out.println(commandStr);
        exeCmd(commandStr);
    }

    //执行cmd命令
    private static String exeCmd(String commandStr) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}  
