package com.wechat.push.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    /**
     * 地区编号
     */
    private String cityCode;

    /**
     * 恋爱
     */
    private String loveDay;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 标题
     */
    private String title;

    public User(String cityCode, String loveDay, String birthday, String title) {
        this.cityCode = cityCode;
        this.loveDay = loveDay;
        this.birthday = birthday;
        this.title = title;
    }
}
