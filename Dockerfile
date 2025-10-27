FROM amazoncorretto:21

COPY target/aps-arq-obj-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]