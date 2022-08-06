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
        USER_INFO.put(ZHOU_TOKEN,new User(SHANGHAI_CODE,"2022-04-06","2022-08-27"));

        USER_INFO.put(JIANG_TOKEN,new User(FUZHOU_CODE,null,null));

        USER_INFO.put(YANG_TOKEN,new User(FUZHOU_CODE,null,null));

        USER_INFO.put(DONG_YI_TOKEN,new User(FUZHOU_CODE,null,null));
    }
}
