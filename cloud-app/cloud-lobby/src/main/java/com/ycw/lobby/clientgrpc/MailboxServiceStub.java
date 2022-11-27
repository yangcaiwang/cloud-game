package com.ycw.lobby.clientgrpc;

import com.ycw.grpc.*;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * @Classname MailboxServiceStub
 * @Description 邮件gprc客户端
 * </p>
 */
@Slf4j
@Service
@DependsOn("springBeanUtil")
public class MailboxServiceStub {
    @GrpcClient("cloud-mailbox")
    private MailboxProtoServiceGrpc.MailboxProtoServiceBlockingStub stub;

    public Long send(List<MailboxProto> mailboxProtoList) {
        SendRequest sendRequest = SendRequest.newBuilder().addAllMailboxProto(mailboxProtoList).build();
        SendResponse sendResponse = stub.send((sendRequest));
        return sendResponse.getSendSuccess();
    }

    public String status(Long userId) {
        StatusRequest statusRequest = StatusRequest.newBuilder().setUserId(userId).build();
        StatusResponse statusResponse = stub.status(statusRequest);
        return statusResponse.toString();
    }

    public Long collect(String mailboxId) {
        CollectRequest collectRequest = CollectRequest.newBuilder().setMailboxId(mailboxId).build();
        CollectResponse collectResponse = stub.collect(collectRequest);
        return collectResponse.getCollectSuccess();
    }
}
