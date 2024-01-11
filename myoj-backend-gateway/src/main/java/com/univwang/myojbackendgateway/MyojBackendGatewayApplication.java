package com.univwang.myojbackendgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients({"com.univwang.myojbackendserviceclient.service"})
public class MyojBackendGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyojBackendGatewayApplication.class, args);
    }

}
