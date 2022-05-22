package com.cym.crowd.config;

import java.util.LinkedHashSet;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cym
 */
@Data
@Component
@ConfigurationProperties("spring.cloud.gateway")
public class NotAuthUrlProperties {

  private LinkedHashSet<String> shouldSkipUrls;
}
