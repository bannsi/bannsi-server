FROM openjdk:11

ENV ENCRYPT_KEY ZZZINHO
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} gateway-0.0.1-SNAPSHOT.jar

EXPOSE 5555


ENTRYPOINT ["java","-jar","/gateway-0.0.1-SNAPSHOT.jar"]