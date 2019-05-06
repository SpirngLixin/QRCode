package com.bjpowernode;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName:QRCodeTest
 * package:com.bjpowernode
 * company:www.bjpowernode.com
 *
 * @Date:2018/7/5 11:08
 * @Author:guoxin
 */
public class QRCodeTest {

    @Test
    public void generateQRCode() throws WriterException, IOException {

        //使用fastjson生成json格式的字符串
        //1.创建一个json对象
        JSONObject jsonObject = new JSONObject();
        //2.往json对象里放值
        jsonObject.put("company","www.bjpowernode.com");
        JSONObject address = new JSONObject();
        address.put("province","河南省");
        address.put("city","安阳市");
        jsonObject.put("address",address);
        jsonObject.put("author","guoxin");

//        String content = jsonObject.toString();
        String content = "weixin://wxpay/bizpayurl?pr=d5WZDiE";


        Integer width = 200;
        Integer height = 200;

        Map<EncodeHintType,Object> hint = new ConcurrentHashMap<EncodeHintType, Object>();
        hint.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        //生成一个矩阵对象
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hint);

        String filePath = "d://";
        String fileName = "QRCode.jpg";

        //创建一个路径对象
        Path path = FileSystems.getDefault().getPath(filePath,fileName);

        //将矩阵对象转换为图片
        MatrixToImageWriter.writeToPath(bitMatrix,"jpg",path);
    }
}
