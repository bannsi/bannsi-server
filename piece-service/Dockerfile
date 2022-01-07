FROM openjdk:11

ENV ENCRYPT_KEY ZZZINHO
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} piece-service-0.0.1-SNAPSHOT.jar

EXPOSE 8081


ENTRYPOINT ["java","-jar","/piece-service-0.0.1-SNAPSHOT.jar"]