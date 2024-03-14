FROM openjdk:21-jdk
WORKDIR /app
COPY target/beancards-0.0.1-SNAPSHOT.jar beancards-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "beancards-0.0.1-SNAPSHOT.jar"]