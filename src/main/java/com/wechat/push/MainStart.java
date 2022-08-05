package com.wechat.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *@ClassName MainStart
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 15:44
 */

@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan("com.wechat.push.configuration")
public class MainStart {
    public static void main(String[] args) {
        SpringApplication.run(MainStart.class, args);
    }
}
