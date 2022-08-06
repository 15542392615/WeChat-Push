package com.wechat.push.service;

import org.springframework.stereotype.Component;

public interface PushService {

    void push(String userToken,String templateId);
}
