package com.cym.crowd;


import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cym
 * @date 2022-05-17  14:16
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalDispose
@MapperScan("com.cym.crowd.mapper")
public class MysqlMain {

  public static void main(String[] args) {
    SpringApplication.run(MysqlMain.class, args);
  }

}
