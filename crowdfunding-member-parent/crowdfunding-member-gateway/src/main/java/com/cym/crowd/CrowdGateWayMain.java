package com.cym.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cym
 * @date 2022-05-18  23:21
 * @description crowd项目网关模块启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CrowdGateWayMain {

  public static void main(String[] args) {
    SpringApplication.run(CrowdGateWayMain.class, args);
  }
}
