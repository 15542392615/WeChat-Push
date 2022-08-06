package com.wechat.push.core;

public interface PushCoreService {

    void doPush(String userToken,String templateId);
}
