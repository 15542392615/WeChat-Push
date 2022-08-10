package com.wechat.push.controller;

/**
 *@ClassName PushController
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 15:48
 */

import com.wechat.push.core.PushCoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.wechat.push.model.CommonConstants.*;

@RestController
public class PushController {

    @Resource
    private PushCoreService pushCoreService;

    /**
     * 发送微信Push
     * http://127.0.0.1:9999/push?userToken=jiangjiang&templateId=t2
     */
    @GetMapping("/push")
    public void push(@RequestParam(value = "userToken", required = false) String userToken,
                     @RequestParam(value = "templateId", required = false) String templateId) {
        if (StringUtils.isBlank(userToken)) {
            userToken = DONG_YI_TOKEN;
        }
        if (StringUtils.isBlank(templateId)) {
            templateId = "t1";
        }
        pushCoreService.doPush(userToken,templateId);
    }
}