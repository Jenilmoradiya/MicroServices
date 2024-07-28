FROM openjdk:17-jdk
COPY target/gateway.jar .
EXPOSE 8084
ENTRYPOINT["java","-jar","gateway.jar"]