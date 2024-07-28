FROM openjdk:17-jdk
COPY target/service-reg.jar .
EXPOSE 8761
ENTRYPOINT["java","-jar","service-reg.jar"]