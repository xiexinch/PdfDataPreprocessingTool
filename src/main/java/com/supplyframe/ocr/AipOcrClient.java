package com.supplyframe.ocr;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.supplyframe.entity.Location;
import com.supplyframe.entity.TextBox;
import com.supplyframe.tools.BaiduConfig;
import com.supplyframe.tools.PathConfig;
import com.supplyframe.tools.Utils;
import org.json.JSONObject;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AipOcrClient extends AipOcr {

    private static AipOcrClient INSTANCE = null;

    private AipOcrClient() {
        super(BaiduConfig.APP_ID, BaiduConfig.API_KEY, BaiduConfig.SECRET_KEY);
    }

    public static AipOcrClient getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (AipOcrClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AipOcrClient();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws InterruptedException {
        AipOcrClient client = AipOcrClient.getINSTANCE();
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        List<String> imagePath = Utils.getFilePaths(PathConfig.IMAGEPATH.getPath());
        //String path = imagePath.get(0);



//        for (int i = 0; i < 450; i++) {
        for (String path: imagePath) {
            //String path = imagePath.get(i);
            JSONObject res = client.general(path, new HashMap<String, String>());

            List<TextBox> textBoxList =  JSON.parseArray(res.get("words_result").toString(), TextBox.class);

            List<String> lines = Utils.textBoxListToStringList(textBoxList);

            Utils.writeLinesToFile(new StringBuilder().append(PathConfig.GTPATH.getPath()).append(path.substring(PathConfig.IMAGEPATH.getPath().length())).append(".txt").toString(), lines);

            Thread.sleep(500);
        }

    }


}












