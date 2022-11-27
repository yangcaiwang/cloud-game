package com.ycw.mailbox.servicegrpc;

import com.ycw.common.propitem.PropItemStoreStruct;
import com.ycw.grpc.*;
import com.ycw.mailbox.entity.Mailbox;
import com.ycw.mailbox.service.MailboxService;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @Classname MailboxServiceGrpc
 * @Description 邮件gprc服务端
 * </p>
 */
@Slf4j
@GrpcService
public class MailboxServiceGrpc extends MailboxProtoServiceGrpc.MailboxProtoServiceImplBase {
    @Autowired
    private MailboxService mailboxService;

    /**
     * <pre>
     * 发送邮件
     * </pre>
     */
    @Override
    public void send(com.ycw.grpc.SendRequest request,
                     io.grpc.stub.StreamObserver<com.ycw.grpc.SendResponse> responseObserver) {
        List<Mailbox> mailboxList = mailboxProtoConverterMailbox(request.getMailboxProtoList());
        long success = 0;
        for (Mailbox mailbox : mailboxList
        ) {
            mailbox = mailboxService.sendMailbox(mailbox);
            if (mailbox != null) {
                success++;
            }
        }
        if (success == mailboxList.size()) {
            responseObserver.onNext(SendResponse.newBuilder().setSendSuccess(success).build());
            responseObserver.onCompleted();
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <pre>
     * 查看邮件
     * </pre>
     */
    @Override
    public void status(com.ycw.grpc.StatusRequest request,
                       io.grpc.stub.StreamObserver<com.ycw.grpc.StatusResponse> responseObserver) {
        long userId = request.getUserId();
        List<Mailbox> mailboxList = mailboxService.getMailbox(userId);
        List<MailboxProto> mailboxProtoList = mailboxConverterMailboxProto(mailboxList);
        StatusResponse statusResponse = StatusResponse.newBuilder().addAllMailboxProto(mailboxProtoList).build();
        responseObserver.onNext(statusResponse);
//        log.info("[status]resp: {}", statusResponse);
        responseObserver.onCompleted();
        log.info("[status]resp: {}", "completed!");
    }

    /**
     * <pre>
     * 领取邮件
     * </pre>
     */
    @Override
    public void collect(com.ycw.grpc.CollectRequest request,
                        io.grpc.stub.StreamObserver<com.ycw.grpc.CollectResponse> responseObserver) {
        long collectSuccess = mailboxService.collectMailbox(request.getMailboxId());
        responseObserver.onNext(CollectResponse.newBuilder().setCollectSuccess(collectSuccess).build());
        responseObserver.onCompleted();
    }

    public List<MailboxProto> mailboxConverterMailboxProto(List<Mailbox> mailboxList) {
        return mailboxList.stream().map(mailbox -> MailboxProto.newBuilder()
                .setMailboxId(mailbox.getId().toString())
                .setUserId(mailbox.getUserId())
                .setTitle(mailbox.getTitle())
                .setBody(mailbox.getBody())
                .setAppendix(mailbox.getAppendix())
                .setMailType(mailbox.getMailType())
                .setMailTypeDescription(mailbox.getMailTypeDescription())
                .setCollectAt(mailbox.getCollectAt().getEpochSecond())
                .setExpiredAt(mailbox.getExpiredAt().getEpochSecond())
                .setCreateAt(mailbox.getCreateAt().getEpochSecond())
                .build()
        ).collect(Collectors.toList());
    }

    public List<Mailbox> mailboxProtoConverterMailbox(List<MailboxProto> mailboxList) {
        return mailboxList.stream().map(mailboxProto -> {
            Mailbox mailbox = new Mailbox();
            mailbox.setUserId(mailboxProto.getUserId())
                    .setTitle(mailboxProto.getTitle())
                    .setBody(mailboxProto.getBody())
                    .setAppendix(mailboxProto.getAppendix())
                    .setMailType(mailboxProto.getMailType())
                    .setMailTypeDescription(mailboxProto.getMailTypeDescription())
                    .setCollectAt(Instant.ofEpochSecond(mailboxProto.getCollectAt()))
                    .setExpiredAt(Instant.ofEpochSecond(mailboxProto.getExpiredAt()))
                    .setCreateAt(Instant.ofEpochSecond(mailboxProto.getCreateAt()));
            return mailbox;
        }).collect(Collectors.toList());
    }

    public List<PropItemStoreStruct> propItemProtoConverterPropItemStoreStruct(List<PropItemProto> propItemProtoList) {
        return propItemProtoList.stream().map(propItemProto -> PropItemStoreStruct.PropItemStoresStructFactory.buildPropItemStoreStruct(propItemProto.getItemConfigType(), propItemProto.getValue())).collect(Collectors.toList());
    }
}
