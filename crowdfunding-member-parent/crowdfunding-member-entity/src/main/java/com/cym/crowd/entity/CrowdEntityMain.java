package com.cym.crowd.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cym
 * @date 2022-06-05  19:30
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CrowdEntityMain {
public static void main(String[] args){
  SpringApplication.run(CrowdEntityMain.class, args);
}
}
