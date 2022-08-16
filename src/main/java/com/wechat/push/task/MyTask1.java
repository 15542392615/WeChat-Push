package com.wechat.push.task;

import com.wechat.push.core.PushCoreService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyTask1 extends IJobHandler{

    @Resource
    private PushCoreService pushCoreService;
    public void testHelloWord() {
        pushCoreService.doPush("1","1");
    }

    @Override
    public void execute() throws Exception {
        System.out.println("this is MyTask1");
        testHelloWord();
    }
}
