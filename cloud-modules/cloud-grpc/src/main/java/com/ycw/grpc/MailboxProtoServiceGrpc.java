package com.ycw.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.0)",
    comments = "Source: Mailbox.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MailboxProtoServiceGrpc {

  private MailboxProtoServiceGrpc() {}

  public static final String SERVICE_NAME = "MailboxProtoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ycw.grpc.SendRequest,
      com.ycw.grpc.SendResponse> getSendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "send",
      requestType = com.ycw.grpc.SendRequest.class,
      responseType = com.ycw.grpc.SendResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ycw.grpc.SendRequest,
      com.ycw.grpc.SendResponse> getSendMethod() {
    io.grpc.MethodDescriptor<com.ycw.grpc.SendRequest, com.ycw.grpc.SendResponse> getSendMethod;
    if ((getSendMethod = MailboxProtoServiceGrpc.getSendMethod) == null) {
      synchronized (MailboxProtoServiceGrpc.class) {
        if ((getSendMethod = MailboxProtoServiceGrpc.getSendMethod) == null) {
          MailboxProtoServiceGrpc.getSendMethod = getSendMethod =
              io.grpc.MethodDescriptor.<com.ycw.grpc.SendRequest, com.ycw.grpc.SendResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "send"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ycw.grpc.SendRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ycw.grpc.SendResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MailboxProtoServiceMethodDescriptorSupplier("send"))
              .build();
        }
      }
    }
    return getSendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ycw.grpc.StatusRequest,
      com.ycw.grpc.StatusResponse> getStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Status",
      requestType = com.ycw.grpc.StatusRequest.class,
      responseType = com.ycw.grpc.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ycw.grpc.StatusRequest,
      com.ycw.grpc.StatusResponse> getStatusMethod() {
    io.grpc.MethodDescriptor<com.ycw.grpc.StatusRequest, com.ycw.grpc.StatusResponse> getStatusMethod;
    if ((getStatusMethod = MailboxProtoServiceGrpc.getStatusMethod) == null) {
      synchronized (MailboxProtoServiceGrpc.class) {
        if ((getStatusMethod = MailboxProtoServiceGrpc.getStatusMethod) == null) {
          MailboxProtoServiceGrpc.getStatusMethod = getStatusMethod =
              io.grpc.MethodDescriptor.<com.ycw.grpc.StatusRequest, com.ycw.grpc.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Status"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ycw.grpc.StatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ycw.grpc.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MailboxProtoServiceMethodDescriptorSupplier("Status"))
              .build();
        }
      }
    }
    return getStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ycw.grpc.CollectRequest,
      com.ycw.grpc.CollectResponse> getCollectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Collect",
      requestType = com.ycw.grpc.CollectRequest.class,
      responseType = com.ycw.grpc.CollectResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ycw.grpc.CollectRequest,
      com.ycw.grpc.CollectResponse> getCollectMethod() {
    io.grpc.MethodDescriptor<com.ycw.grpc.CollectRequest, com.ycw.grpc.CollectResponse> getCollectMethod;
    if ((getCollectMethod = MailboxProtoServiceGrpc.getCollectMethod) == null) {
      synchronized (MailboxProtoServiceGrpc.class) {
        if ((getCollectMethod = MailboxProtoServiceGrpc.getCollectMethod) == null) {
          MailboxProtoServiceGrpc.getCollectMethod = getCollectMethod =
              io.grpc.MethodDescriptor.<com.ycw.grpc.CollectRequest, com.ycw.grpc.CollectResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Collect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ycw.grpc.CollectRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ycw.grpc.CollectResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MailboxProtoServiceMethodDescriptorSupplier("Collect"))
              .build();
        }
      }
    }
    return getCollectMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MailboxProtoServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MailboxProtoServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MailboxProtoServiceStub>() {
        @java.lang.Override
        public MailboxProtoServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MailboxProtoServiceStub(channel, callOptions);
        }
      };
    return MailboxProtoServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MailboxProtoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MailboxProtoServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MailboxProtoServiceBlockingStub>() {
        @java.lang.Override
        public MailboxProtoServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MailboxProtoServiceBlockingStub(channel, callOptions);
        }
      };
    return MailboxProtoServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MailboxProtoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MailboxProtoServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MailboxProtoServiceFutureStub>() {
        @java.lang.Override
        public MailboxProtoServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MailboxProtoServiceFutureStub(channel, callOptions);
        }
      };
    return MailboxProtoServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MailboxProtoServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 发送邮件
     * </pre>
     */
    public void send(com.ycw.grpc.SendRequest request,
        io.grpc.stub.StreamObserver<com.ycw.grpc.SendResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendMethod(), responseObserver);
    }

    /**
     * <pre>
     * 查看邮件
     * </pre>
     */
    public void status(com.ycw.grpc.StatusRequest request,
        io.grpc.stub.StreamObserver<com.ycw.grpc.StatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * 领取邮件
     * </pre>
     */
    public void collect(com.ycw.grpc.CollectRequest request,
        io.grpc.stub.StreamObserver<com.ycw.grpc.CollectResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCollectMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.ycw.grpc.SendRequest,
                com.ycw.grpc.SendResponse>(
                  this, METHODID_SEND)))
          .addMethod(
            getStatusMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.ycw.grpc.StatusRequest,
                com.ycw.grpc.StatusResponse>(
                  this, METHODID_STATUS)))
          .addMethod(
            getCollectMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.ycw.grpc.CollectRequest,
                com.ycw.grpc.CollectResponse>(
                  this, METHODID_COLLECT)))
          .build();
    }
  }

  /**
   */
  public static final class MailboxProtoServiceStub extends io.grpc.stub.AbstractAsyncStub<MailboxProtoServiceStub> {
    private MailboxProtoServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MailboxProtoServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MailboxProtoServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 发送邮件
     * </pre>
     */
    public void send(com.ycw.grpc.SendRequest request,
        io.grpc.stub.StreamObserver<com.ycw.grpc.SendResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 查看邮件
     * </pre>
     */
    public void status(com.ycw.grpc.StatusRequest request,
        io.grpc.stub.StreamObserver<com.ycw.grpc.StatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 领取邮件
     * </pre>
     */
    public void collect(com.ycw.grpc.CollectRequest request,
        io.grpc.stub.StreamObserver<com.ycw.grpc.CollectResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCollectMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MailboxProtoServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MailboxProtoServiceBlockingStub> {
    private MailboxProtoServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MailboxProtoServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MailboxProtoServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 发送邮件
     * </pre>
     */
    public com.ycw.grpc.SendResponse send(com.ycw.grpc.SendRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 查看邮件
     * </pre>
     */
    public com.ycw.grpc.StatusResponse status(com.ycw.grpc.StatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 领取邮件
     * </pre>
     */
    public com.ycw.grpc.CollectResponse collect(com.ycw.grpc.CollectRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCollectMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MailboxProtoServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MailboxProtoServiceFutureStub> {
    private MailboxProtoServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MailboxProtoServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MailboxProtoServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 发送邮件
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ycw.grpc.SendResponse> send(
        com.ycw.grpc.SendRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 查看邮件
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ycw.grpc.StatusResponse> status(
        com.ycw.grpc.StatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStatusMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 领取邮件
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ycw.grpc.CollectResponse> collect(
        com.ycw.grpc.CollectRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCollectMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND = 0;
  private static final int METHODID_STATUS = 1;
  private static final int METHODID_COLLECT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MailboxProtoServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MailboxProtoServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND:
          serviceImpl.send((com.ycw.grpc.SendRequest) request,
              (io.grpc.stub.StreamObserver<com.ycw.grpc.SendResponse>) responseObserver);
          break;
        case METHODID_STATUS:
          serviceImpl.status((com.ycw.grpc.StatusRequest) request,
              (io.grpc.stub.StreamObserver<com.ycw.grpc.StatusResponse>) responseObserver);
          break;
        case METHODID_COLLECT:
          serviceImpl.collect((com.ycw.grpc.CollectRequest) request,
              (io.grpc.stub.StreamObserver<com.ycw.grpc.CollectResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MailboxProtoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MailboxProtoServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ycw.grpc.Mailbox.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MailboxProtoService");
    }
  }

  private static final class MailboxProtoServiceFileDescriptorSupplier
      extends MailboxProtoServiceBaseDescriptorSupplier {
    MailboxProtoServiceFileDescriptorSupplier() {}
  }

  private static final class MailboxProtoServiceMethodDescriptorSupplier
      extends MailboxProtoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MailboxProtoServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MailboxProtoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MailboxProtoServiceFileDescriptorSupplier())
              .addMethod(getSendMethod())
              .addMethod(getStatusMethod())
              .addMethod(getCollectMethod())
              .build();
        }
      }
    }
    return result;
  }
}
