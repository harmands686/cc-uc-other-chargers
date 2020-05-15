FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bulkordersplitterservice.jar
ENTRYPOINT ["java","-jar","/bulkordersplitterservice.jar"]
