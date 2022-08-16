package com.wechat.push.model;

import com.wechat.push.model.po.User;

import java.util.HashMap;

import static com.wechat.push.model.CommonConstants.*;

/**
 * 用户信息
 * */
public final class UserConstants {

    public static final HashMap<String, User> USER_INFO = new HashMap<>();

    static {

        USER_INFO.put(JIANG_TOKEN,new User(FUZHOU_CODE,"2021-03-28","2023-02-20","姜姜给洋洋的专属提醒:多喝烫水!"));

        USER_INFO.put(YANG_TOKEN,new User(FUZHOU_CODE,"2021-03-31","2023-02-20","姜姜给洋洋的专属提醒:多喝烫水!"));

        USER_INFO.put(DONG_YI_TOKEN,new User(SHANGHAI_CODE,"2021-11-14","2023-03-03","付东一给YXT的专属提醒:多喝开水!"));
    }
}
