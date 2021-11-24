FROM openjdk:11

ENV ENCRYPT_KEY ZZZINHO
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} eurekasvr-0.0.1-SNAPSHOT.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","/eurekasvr-0.0.1-SNAPSHOT.jar"]