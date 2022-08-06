package com.wechat.push.core.impl;

import com.wechat.push.core.PushCoreService;
import com.wechat.push.service.PushService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PushCoreServiceImpl implements PushCoreService {

    @Resource
    private PushService pushService;

    @Override
    public void doPush(String openId,String templateId) {
        pushService.push(openId,templateId);
    }
}
