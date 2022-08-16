package com.wechat.push.task;

import com.alibaba.fastjson.JSON;
import com.wechat.push.core.PushCoreService;
import com.wechat.push.model.CommonConstants;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

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
        Param param = JSON.parseObject(XxlJobHelper.getJobParam(), Param.class);
        if (Objects.isNull(param)) {
            XxlJobHelper.handleFail("Param is null.");
        }

        XxlJobHelper.log("开始task, Param:{}", XxlJobHelper.getJobParam());
        pushCoreService.doPush(param.userToken, param.template);
    }

    public static class Param {
        public String userToken = CommonConstants.DONG_YI_TOKEN;
        public String template = "t1";
    }
}
