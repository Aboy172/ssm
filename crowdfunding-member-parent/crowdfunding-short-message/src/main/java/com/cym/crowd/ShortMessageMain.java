package com.cym.crowd;

import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
=======
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
>>>>>>> dev

/**
 * @author cym
 * @date 2022-06-02  21:01
 * @description 短信服务启动类
 */
@SpringBootApplication
<<<<<<< HEAD
=======
@EnableDiscoveryClient
>>>>>>> dev
public class ShortMessageMain {
  public static void main(String[] args){
    SpringApplication.run(ShortMessageMain.class, args);
  }
}
