package com.ycw.operatelog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OperateLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperateLogApplication.class, args);
        log.info("cloud-opertatelog 启动成功");
    }
}
