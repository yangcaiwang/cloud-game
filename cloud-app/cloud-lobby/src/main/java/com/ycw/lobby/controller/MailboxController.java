package com.ycw.lobby.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ycw.common.appendix.SystemPropItem;
import com.ycw.common.enums.MailType;
import com.ycw.common.propitem.Coins;
import com.ycw.common.propitem.CoinsUsd;
import com.ycw.common.propitem.PropItemStoreStruct;
import com.ycw.common.response.R;

import com.ycw.grpc.MailboxProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Date 2022/9/8 5:34 PM
 */
@Slf4j
@RestController
@RequestMapping("/app")
public class MailboxController {
    @Autowired
    com.ycw.lobby.clientgrpc.MailboxServiceStub mailboxServiceStub;

    @PostMapping("/mailbox/send")
    public R sendMailbox() throws JsonProcessingException {
        List<PropItemStoreStruct> propItemStoreStructList = Arrays.asList(Coins.buildPropItemStoreStruct("100"), CoinsUsd.buildPropItemStoreStruct("10"));
        SystemPropItem systemPropItem = new SystemPropItem();
        systemPropItem.setPropItemStoreStructList(propItemStoreStructList);
        MailboxProto mailboxProto = MailboxProto.newBuilder().setUserId(10001L)
                .setTitle("test001")
                .setBody("test001")
                .setAppendix(new ObjectMapper().writeValueAsString(systemPropItem))
                .setMailType(MailType.DEVELOPER.getType())
                .setMailTypeDescription(MailType.DEVELOPER.getDescription())
                .setCollectAt(Instant.EPOCH.getEpochSecond())
                .setExpiredAt(Instant.now().plus(Duration.ofDays(30)).getEpochSecond())
                .setCreateAt(Instant.now().getEpochSecond())
                .build();
        Long success = mailboxServiceStub.send(Arrays.asList(mailboxProto));
        return R.ok(success);
    }

    @GetMapping("/mailbox/status")
    public R mailboxStatus(@RequestParam Long userId) throws JsonProcessingException {
        return R.ok(mailboxServiceStub.status(userId));
    }

    @GetMapping("/mailbox/collect")
    public R collectMailbox(@RequestParam String mailboxId) {
        return R.ok(mailboxServiceStub.collect(mailboxId));
    }
}
