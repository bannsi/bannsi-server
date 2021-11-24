FROM openjdk:11

ENV ENCRYPT_KEY ZZZINHO
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} confsvr-0.0.1-SNAPSHOT.jar

EXPOSE 8888


ENTRYPOINT ["java","-jar","/confsvr-0.0.1-SNAPSHOT.jar"]