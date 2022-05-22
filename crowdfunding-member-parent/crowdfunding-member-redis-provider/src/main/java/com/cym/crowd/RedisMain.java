package com.cym.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cym
 * @date 2022-05-18  21:18
 * @description redis启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RedisMain {

  public static void main(String[] args) {
    SpringApplication.run(RedisMain.class, args);
  }

}
