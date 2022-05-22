package com.cym.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author cym
 * @date 2022-05-17  13:12
 * @description nacos注册中心启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApiCrowdMain {

  public static void main(String[] args) {
    SpringApplication.run(ApiCrowdMain.class, args);
  }
}
