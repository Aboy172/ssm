package com.cym.atcrowdfunding03admincomponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 86152
 */
@SpringBootApplication()
@EnableTransactionManagement
public class Atcrowdfunding03AdminComponentApplication {

    public static void main (String[] args) {
        SpringApplication.run(Atcrowdfunding03AdminComponentApplication.class,args);
    }

}
