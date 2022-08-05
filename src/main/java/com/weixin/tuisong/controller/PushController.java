package com.weixin.tuisong.controller;

/**
 *@ClassName PushController
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 15:48
 */
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weixin.tuisong.util.Pusher;

@RestController
public class PushController {
    //要推送的用户openid
    private static String mxp = "oJP9r5g7v3_W2W5H2m4PeT5Y1T1Y";
    private static String zyd = "odbd-6U6ygdSTCwldsJ6qs0kxXeA";


    /**
     * 微信测试账号推送
     *
     */
    @GetMapping("/push")
    public void push(@RequestParam("openId") String openId) {
        if (StringUtils.isBlank(openId)) {
            openId = mxp;
        }
        Pusher.push(openId);
    }

    /**
     * 微信测试账号推送
     * */
    @GetMapping("/push/zyd")
    public void pushZyd() {
        Pusher.push(zyd);
    }


    /**
     * 微信测试账号推送
     * */
    @GetMapping("/push/{id}")
    public void pushId(@PathVariable("id") String id) {
        Pusher.push(id);
    }
}