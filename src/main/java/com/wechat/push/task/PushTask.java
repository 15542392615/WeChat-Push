package com.wechat.push.task;

import com.wechat.push.core.PushCoreService;
import com.wechat.push.service.PushService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *@ClassName JobWorker
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 16:00
 */
@Component
public class PushTask {

    @Resource
    private PushCoreService pushCoreService;

/*    @Scheduled(cron = "0 30 7 * * ?")
    public void goodMorning(){
        pushCoreService.doPush(openId);
    }*/

}
