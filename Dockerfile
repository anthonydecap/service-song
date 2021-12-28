FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.13_8
EXPOSE 8082
EXPOSE 27017
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]