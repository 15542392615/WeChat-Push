package com.weixin.tuisong.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fudongyi
 * @date 2022/8/5
 */
@Data
@ConfigurationProperties("third.caihongpi")
public class ThirdCaihongpiConfiguration {
  public String key;
  public String url;
  public String name;
  public String defaultContent;
}
