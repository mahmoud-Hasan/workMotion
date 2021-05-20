FROM adoptopenjdk:11.0.11_9-jdk-hotspot
COPY target/task-0.0.1-SNAPSHOT.jar task-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/task-0.0.1-SNAPSHOT.jar"]