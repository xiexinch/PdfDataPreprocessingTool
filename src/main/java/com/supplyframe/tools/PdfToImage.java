package com.supplyframe.tools;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfToImage {
    //经过测试,dpi为96,100,105,120,150,200中,105显示效果较为清晰,体积稳定,dpi越高图片体积越大,一般电脑显示分辨率为96
    public static final float DEFAULT_DPI = 105;
    //默认转换的图片格式为jpg
    public static final String DEFAULT_FORMAT = "jpg";

    private static Logger logger = Logger.getLogger(PdfToImage.class.getName());

    public static void pdfToImg(String pdfPath, String imgPath) throws IOException {
        int width = 0;
        int[] singleImgRGB;
        int shiftHeight = 0;
        BufferedImage bufferedImage = null;
        PDDocument pdDocument = null;
        try {
            pdDocument  = PDDocument.load(new File(pdfPath));
        } catch (IOException e) {
            logger.log(Level.WARNING, pdfPath + e.getMessage());
            return;
        }

        PDFRenderer renderer = new PDFRenderer(pdDocument);

        StringBuilder sb = new StringBuilder();
        sb.append(imgPath);
        sb.append(pdfPath.substring(pdfPath.lastIndexOf('/') + 1, pdfPath.length() - 4));
        sb.append('-');
        for (int i = 0; i < pdDocument.getNumberOfPages(); i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
            int imageHeight = image.getHeight();
            int imageWidth = image.getWidth();
            width = imageWidth;
            bufferedImage = new BufferedImage(width, imageHeight, BufferedImage.TYPE_INT_RGB);
            singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
            bufferedImage.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
            sb.append(i);
            sb.append(".jpg");
            ImageIO.write(bufferedImage, DEFAULT_FORMAT, new File(sb.toString()));
            sb.delete(sb.length() - (4 + String.valueOf(i).length()), sb.length());

        }
        pdDocument.close();
        //ImageIO.write(bufferedImage, DEFAULT_FORMAT, new File(imgPath));
    }

//    public static void main(String[] args) throws IOException {
//        String pdfPath = "/Users/xiexinchen/IdeaProjects/PdfDataPreprocessingTool/data/" + "LM358.pdf";
//        String imgPath = "/Users/xiexinchen/IdeaProjects/PdfDataPreprocessingTool/image/";
//        pdfToImg(pdfPath, imgPath);
//    }

}
