package com.wechat.push.service;

public interface PushService {

    void push(String userToken,String templateId);
}
