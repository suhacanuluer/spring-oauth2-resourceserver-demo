package com.suhacan.springoauth2resourceserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
public class SpringOauth2ResourceserverDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOauth2ResourceserverDemoApplication.class, args);
    }

}
