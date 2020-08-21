package com.supplyframe.tools;

import java.io.IOException;
import java.util.List;

public class ProcessPdf {
    public static void main(String[] args) {
        List<String> pdfPaths = Utils.getFilePaths(PathConfig.PDFPATH.getPath());
        String imagePath = PathConfig.IMAGEPATH.getPath();
        for (String path: pdfPaths) {
            try {
                PdfToImage.pdfToImg(path, imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
