package com.wechat.push.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wechat.push.configuration.ThirdBaiduConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName Tianqi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 16:45
 */
@Component
public class Tianqi {
    private static String ak = "xxx";
    private static String district_id = "320111";

    @Autowired
    private ThirdBaiduConfiguration thirdBaiduConfiguration;

    @PostConstruct
    private void init() {
        ak = thirdBaiduConfiguration.ak;
        district_id = thirdBaiduConfiguration.districtId;
    }

    public static JSONObject getNanjiTianqi() {
        String result = null;
        JSONObject today = new JSONObject();
        try {
            result = HttpUtil.getUrl("https://api.map.baidu.com/weather/v1/?district_id=" + district_id + "&data_type=all&ak=" + ak);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("message").equals("success")) {
                JSONArray arr = jsonObject.getJSONObject("result").getJSONArray("forecasts");
                today = arr.getJSONObject(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }

    public static void main(String[] args) {
        System.out.println(getNanjiTianqi());
    }
}