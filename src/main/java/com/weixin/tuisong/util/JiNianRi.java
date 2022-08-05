package com.weixin.tuisong.util;

import com.weixin.tuisong.configuration.AnniversaryConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName JiNianRi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 17:32
 */
@Slf4j
@Component
public class JiNianRi {
    /**
     * 恋爱
     */
    static String lianAi = "2018-05-21";
    /**
     * 领证
     */
    static String linZheng = "2022-03-19";
    /**
     * 结婚
     */
    static String jieHun = "2022-07-08";
    /**
     * 生日
     */
    static String shengRi = "2023-03-02";

    @Autowired
    private AnniversaryConfiguration anniversaryConfiguration;

    @PostConstruct
    private void init() {
        lianAi = anniversaryConfiguration.lianai;
        linZheng = anniversaryConfiguration.lingzheng;
        jieHun = anniversaryConfiguration.jiehun;
        shengRi = anniversaryConfiguration.shengri;
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 距离date还有多少天
     * @param date
     * @return
     */
    public static int before(String date) {
        return getIntervalDays(date);
    }


    /**
     * 已经过去date多少天
     * @param date
     * @return
     */
    public static int after(String date) {
        return getIntervalDays(date);
    }

    private static int getIntervalDays(String date) {
        int day = 0;
        long now = System.currentTimeMillis();
        try {
            long time = simpleDateFormat.parse(date).getTime() - now;
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            log.error("Calculate interval days error. date:{}, currentMillis:{}", date, now);
            return 0;
        }
        return Math.abs(day);
    }

    public static int getJieHun() {
        return before(jieHun);
    }

    public static int getLinZhen() {
        return before(linZheng);
    }

    public static int getLianAi() {
        return before(lianAi);
    }

    public static int getShengRi(){
        return after(shengRi);
    }

    public static void main(String[] args) {
        System.out.println(getJieHun());
    }


}
