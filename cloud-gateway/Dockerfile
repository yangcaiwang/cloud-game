#FROM java:8
FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} gateway.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","/gateway.jar"]
