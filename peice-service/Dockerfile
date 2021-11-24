FROM openjdk:11

ENV ENCRYPT_KEY ZZZINHO
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} peice-service-0.0.1-SNAPSHOT.jar

EXPOSE 8081


ENTRYPOINT ["java","-jar","/peice-service-0.0.1-SNAPSHOT.jar"]