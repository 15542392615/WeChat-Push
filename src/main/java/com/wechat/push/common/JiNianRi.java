package com.wechat.push.common;

import com.wechat.push.configuration.AnniversaryConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.wechat.push.model.UserConstants.USER_INFO;

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
    static String lianAi = "2022-04-06";
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
    static String shengRi = "2022-08-27";

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
    public static Integer before(String date) {
        return getIntervalDays(date);
    }


    /**
     * 已经过去date多少天
     * @param date
     * @return
     */
    public static Integer after(String date) {
        if (StringUtils.isBlank(date)){
            return null;
        }
        int day = 0;
        long now = System.currentTimeMillis();
        try {
            long time = now - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            log.error("Calculate interval days error. date:{}, currentMillis:{}", date, now);
            return 0;
        }
        return Math.abs(day);
    }

    private static Integer getIntervalDays(String date) {
        if (StringUtils.isBlank(date)){
            return null;
        }
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

    public static Integer getJieHun() {
        return before(jieHun);
    }

    public static Integer getLinZhen() {
        return before(linZheng);
    }

    public static Integer getLianAi(String userToken) {
        return after(USER_INFO.get(userToken).getLoveDay());
    }

    public static Integer getShengRi(String userToken){
        return after(USER_INFO.get(userToken).getBirthday());
    }

}
