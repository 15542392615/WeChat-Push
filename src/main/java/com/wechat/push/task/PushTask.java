package com.wechat.push.task;

import com.wechat.push.core.PushCoreService;
import com.wechat.push.model.CommonConstants;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName JobWorker
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 16:00
 */
@Component
@Slf4j
public class PushTask {

    @Resource
    private PushCoreService pushCoreService;

    @XxlJob(value = "wechatPushJob")
    public void goodMorning() {
        log.info("开始task");
        pushCoreService.doPush(CommonConstants.DONG_YI_TOKEN,"t1");
    }

}
