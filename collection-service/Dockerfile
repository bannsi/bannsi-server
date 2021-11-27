FROM openjdk:11

ENV ENCRYPT_KEY ZZZINHO
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} collection-service-0.0.1-SNAPSHOT.jar

EXPOSE 8082


ENTRYPOINT ["java","-jar","/collection-service-0.0.1-SNAPSHOT.jar"]