FROM openjdk:11

ENV ENCRYPT_KEY ZZZINHO
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} account-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080


ENTRYPOINT ["java","-jar","/account-service-0.0.1-SNAPSHOT.jar"]