FROM bellsoft/liberica-openjdk-alpine:17
VOLUME /tmp
COPY target/registry-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
