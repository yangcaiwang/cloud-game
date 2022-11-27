package com.ycw.mailbox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class MailboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailboxApplication.class, args);
        log.info("cloud-mailbox 启动成功");
    }
}
