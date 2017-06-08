package com.jyx.util.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;

import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 条形码生成器
 */
public class BarcodeUtil {
    // 条形码生成(返回文件流)
    public BufferedImage encode(String contents, int width, int height) throws WriterException {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.EAN_13, codeWidth, height, null);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    // 条形码生成(保存文件)
    public void encode(String contents, int width, int height, String imgPath) throws FileNotFoundException, IOException, WriterException {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.EAN_13, codeWidth, height, null);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(imgPath));

    }

    // 条形码解析
    public String decode(String imgPath) throws IOException, NotFoundException {
        BufferedImage image = null;
        Result result = null;
        image = ImageIO.read(new File(imgPath));
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        result = new MultiFormatReader().decode(bitmap, null);
        return result.getText();

    }
    // 测试代码
//    public static void main(String[] args) {
//        String imgPath = "E:/test2.png";
//        String contents = "6923450657713";
//        try {
//			new BarcodeUtil().encode(contents, 500, 500, imgPath);
//		} catch (IOException | WriterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }

}
