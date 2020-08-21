package com.supplyframe.tools;

import com.supplyframe.entity.Location;
import com.supplyframe.entity.TextBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List getFileNames(String directoryPath) {
        List<String> fileNameList = new ArrayList();
        File dir = new File(directoryPath);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileNameList.add(file.getName());
            }
        } else {
            System.out.println(directoryPath + " is not a dir");
        }
        return fileNameList;
    }

    public static List getFilePaths(String directoryPath) {
        List<String> filePathList = new ArrayList();
        File dir = new File(directoryPath);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                filePathList.add(file.getPath());
            }
        } else {
            System.out.println(directoryPath + " is not a dir");
        }
        return filePathList;
    }

    public static String imageToBase64(String imagePath) {

        return null;
    }

    public static void writeLinesToFile(String path, List<String> lines) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path), true)));
            for (String line: lines) {
                out.write(line);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String> textBoxListToStringList(List<TextBox> textBoxList) {
        List<String> lines = new ArrayList<>();
        for (TextBox textBox: textBoxList) {
            Location location = textBox.getLocation();
            StringBuilder sb = new StringBuilder();
            sb.append(location.getTop());
            sb.append(' ');
            sb.append(location.getLeft());
            sb.append(' ');
            sb.append(location.getWidth());
            sb.append(' ');
            sb.append(location.getHeight());
            sb.append(' ');
            sb.append(textBox.getWords());
            sb.append('\n');
            lines.add(sb.toString());
        }
        return lines;
    }


//    public static void main(String[] args) {
//        List<String> fileNameList =  getFilePaths("/Users/xiexinchen/IdeaProjects/PdfDataPreprocessingTool/data");
//        for (String name: fileNameList) {
//            System.out.println(name);
//        }
//    }
}
