package com.supplyframe.tools;

public enum PathConfig {

    PDFPATH("/Users/xiexinchen/IdeaProjects/PdfDataPreprocessingTool/data/"),
    IMAGEPATH("/Users/xiexinchen/IdeaProjects/PdfDataPreprocessingTool/image/"),
    GTPATH("/Users/xiexinchen/IdeaProjects/PdfDataPreprocessingTool/gt/");

    public String getPath() {
        return path;
    }

    private String path;
    private PathConfig(String path) {
        this.path = path;
    }
}
