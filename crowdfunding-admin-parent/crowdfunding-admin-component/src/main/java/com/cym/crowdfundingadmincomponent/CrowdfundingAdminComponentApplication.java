package com.cym.crowdfundingadmincomponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 86152
 */
@SpringBootApplication
@EnableTransactionManagement
public class CrowdfundingAdminComponentApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrowdfundingAdminComponentApplication.class, args);

  }

}
