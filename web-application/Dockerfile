FROM openjdk:8-jdk-alpine
EXPOSE 8085
VOLUME /tmp
ARG JAR_FILE
COPY ./target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
