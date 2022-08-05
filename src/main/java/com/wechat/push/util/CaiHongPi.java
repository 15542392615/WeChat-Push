package com.wechat.push.util;

import com.alibaba.fastjson.JSONObject;
import com.wechat.push.configuration.ThirdCaihongpiConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName CaiHongPi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 17:26
 */
@Component
public class CaiHongPi {
    private static String key = "xxx";
    private static String url = "http://api.tianapi.com/caihongpi/index?key=";
    private static List<String> jinJuList = new ArrayList<>();
    private static String name = "老婆";
    private static String defaultContent;

    @Autowired
    private ThirdCaihongpiConfiguration thirdCaihongpiConfiguration;

    @PostConstruct
    public void init() {
        key = thirdCaihongpiConfiguration.key;
        url = thirdCaihongpiConfiguration.url;
        name = thirdCaihongpiConfiguration.name;
        defaultContent = thirdCaihongpiConfiguration.defaultContent;
    }

    public static String getCaiHongPi() {
        //默认彩虹屁
        String str = defaultContent;
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.getUrl(url+key).replace("XXX", name));
            if (jsonObject.getIntValue("code") == 200) {
                str = jsonObject.getJSONArray("newslist").getJSONObject(0).getString("content");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 载入金句库
     */
    static {
        InputStream inputStream = CaiHongPi.class.getClassLoader().getResourceAsStream("jinju.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String str = "";
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (!StringUtils.isEmpty(temp)) {
                    str = str + "\r\n" + temp;
                } else {
                    jinJuList.add(str);
                    str = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJinJu() {
        Random random = new Random();
        return jinJuList.get(random.nextInt(jinJuList.size()));
    }

    public static void main(String[] args) {
        System.out.println(getJinJu());
    }
}
