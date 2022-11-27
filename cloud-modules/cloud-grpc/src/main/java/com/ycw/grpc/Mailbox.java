// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Mailbox.proto

package com.ycw.grpc;

public final class Mailbox {
  private Mailbox() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SendRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SendRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SendResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SendResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StatusRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StatusRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StatusResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StatusResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CollectRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CollectRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CollectResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CollectResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_MailboxProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_MailboxProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rMailbox.proto\032\nBase.proto\"2\n\013SendReque" +
      "st\022#\n\014mailboxProto\030\001 \003(\0132\r.MailboxProto\"" +
      "#\n\014SendResponse\022\023\n\013sendSuccess\030\001 \001(\003\"\037\n\r" +
      "StatusRequest\022\016\n\006userId\030\001 \001(\003\"5\n\016StatusR" +
      "esponse\022#\n\014mailboxProto\030\001 \003(\0132\r.MailboxP" +
      "roto\"#\n\016CollectRequest\022\021\n\tmailboxId\030\001 \001(" +
      "\t\")\n\017CollectResponse\022\026\n\016collectSuccess\030\001" +
      " \001(\003\"\307\001\n\014MailboxProto\022\021\n\tmailboxId\030\001 \001(\t" +
      "\022\016\n\006userId\030\002 \001(\003\022\r\n\005title\030\003 \001(\t\022\014\n\004body\030" +
      "\004 \001(\t\022\020\n\010appendix\030\005 \001(\t\022\020\n\010mailType\030\006 \001(" +
      "\003\022\033\n\023mailTypeDescription\030\007 \001(\t\022\021\n\tcollec" +
      "tAt\030\010 \001(\003\022\021\n\texpiredAt\030\t \001(\003\022\020\n\010createAt" +
      "\030\n \001(\0032\231\001\n\023MailboxProtoService\022%\n\004send\022\014" +
      ".SendRequest\032\r.SendResponse\"\000\022+\n\006Status\022" +
      "\016.StatusRequest\032\017.StatusResponse\"\000\022.\n\007Co" +
      "llect\022\017.CollectRequest\032\020.CollectResponse" +
      "\"\000B\031\n\014com.ycw.grpcB\007MailboxP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.ycw.grpc.BaseProto.getDescriptor(),
        });
    internal_static_SendRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_SendRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SendRequest_descriptor,
        new java.lang.String[] { "MailboxProto", });
    internal_static_SendResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_SendResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SendResponse_descriptor,
        new java.lang.String[] { "SendSuccess", });
    internal_static_StatusRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_StatusRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StatusRequest_descriptor,
        new java.lang.String[] { "UserId", });
    internal_static_StatusResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_StatusResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StatusResponse_descriptor,
        new java.lang.String[] { "MailboxProto", });
    internal_static_CollectRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_CollectRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CollectRequest_descriptor,
        new java.lang.String[] { "MailboxId", });
    internal_static_CollectResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_CollectResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CollectResponse_descriptor,
        new java.lang.String[] { "CollectSuccess", });
    internal_static_MailboxProto_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_MailboxProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_MailboxProto_descriptor,
        new java.lang.String[] { "MailboxId", "UserId", "Title", "Body", "Appendix", "MailType", "MailTypeDescription", "CollectAt", "ExpiredAt", "CreateAt", });
    com.ycw.grpc.BaseProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}