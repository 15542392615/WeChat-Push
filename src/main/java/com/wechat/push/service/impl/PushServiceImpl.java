package com.wechat.push.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wechat.push.configuration.ThirdSubConfiguration;
import com.wechat.push.common.CaiHongPi;
import com.wechat.push.common.JiNianRi;
import com.wechat.push.service.PushService;
import com.wechat.push.common.Tianqi;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.wechat.push.model.CommonConstants.TEMPLATE_MAP;
import static com.wechat.push.model.CommonConstants.USER_MAP;

@Component
public class PushServiceImpl implements PushService {
    /**
     * 测试号的appId和secret
     */
    private static String appId = "xxx";
    private static String secret = "6e2a329fd59e83fb87f013cd3a405137";

    @Autowired
    private ThirdSubConfiguration thirdSubConfiguration;

    @PostConstruct
    private void init() {
        appId = thirdSubConfiguration.appId;
        secret = thirdSubConfiguration.secret;
    }

    public void push(String userToken,String templateId){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(USER_MAP.get(userToken))
                .templateId(TEMPLATE_MAP.get(templateId))
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        //填写变量信息，比如天气之类的
        JSONObject todayWeather = Tianqi.getNanjiTianqi(userToken);
        templateMessage.addData(new WxMpTemplateData("riqi",todayWeather.getString("date") + "  "+ todayWeather.getString("week"),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",todayWeather.getString("text_day"),"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low",todayWeather.getString("low") + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("high",todayWeather.getString("high")+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi", CaiHongPi.getCaiHongPi(),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRi.getLianAi(userToken)+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri",JiNianRi.getShengRi(userToken)+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju",CaiHongPi.getJinJu()+"","#C71585"));
        //templateMessage.addData(new WxMpTemplateData("jiehun",JiNianRi.getJieHun()+""));
        templateMessage.addData(new WxMpTemplateData("linzhen",JiNianRi.getLinZhen()+"","#FF6347"));
//        String beizhu = "如果你突然打了个喷嚏 那一定就是我在想你～";
        String beizhu = "付东一给YXT的专属提醒:多喝开水!";
        if(null != JiNianRi.getJieHun() && JiNianRi.getJieHun() % 365 == 0){
            beizhu = "今天是结婚纪念日！";
        }
        if(null != JiNianRi.getLianAi(userToken) && JiNianRi.getLianAi(userToken) % 365 == 0){
            beizhu = "今天是恋爱纪念日！";
        }
        if(null != JiNianRi.getLinZhen() && JiNianRi.getLinZhen() % 365 == 0){
            beizhu = "今天是结婚纪念日！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));


        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
