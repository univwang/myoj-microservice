package com.univwang.myojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.univwang.myojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.univwang")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.univwang.myojbackendserviceclient.service"})
public class MyojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyojBackendUserServiceApplication.class, args);
    }

}
