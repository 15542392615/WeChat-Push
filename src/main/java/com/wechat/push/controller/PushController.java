package com.wechat.push.controller;

/**
 *@ClassName PushController
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 15:48
 */
import com.wechat.push.core.PushCoreService;
import com.wechat.push.service.PushService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.wechat.push.model.CommonConstants.TEMPLATE_MAP;
import static com.wechat.push.model.CommonConstants.USER_MAP;

@RestController
public class PushController {

    @Resource
    private PushCoreService pushCoreService;

    /**
     * 微信测试账号推送
     *
     */
    @GetMapping("/push")
    public void push(@RequestParam("openId") String openId,@RequestParam("templateId") String templateId) {
        if (StringUtils.isBlank(openId)||StringUtils.isBlank(templateId)) {
            openId = USER_MAP.get("jiangjiang");
            templateId = TEMPLATE_MAP.get("t1");
        }
        pushCoreService.doPush(openId,templateId);
    }
}