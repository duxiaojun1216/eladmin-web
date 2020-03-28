package me.zhengjie.service.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成批次号及编号
 */
public class CodeUtlis {

    //批次号
    public static String batch(String area){
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
        String s = sdf.format(date);
        //拼接字符串
        String batch = area+s+"01";
        return batch;
    }

    //编号
    public static String  sNumber(String PerTe,String Housing){
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
        String number = sdf.format(date);
        //拼接
        String s = PerTe+Housing+number;
        return s;
    }
}
