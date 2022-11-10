package com.ycw.lobby;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class LobbyApplication {
    public static void main(String[] args) {
        SpringApplication.run(LobbyApplication.class, args);
        log.info("cloud-lobby 启动成功");
    }
}
