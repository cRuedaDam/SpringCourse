FROM bellsoft/liberica-openjdk-alpine:17
VOLUME /tmp
COPY target/auth-server-0.0.1-SNAPSHOT.jar app.jar
ENV EUREKA_URL null
ENV CONFIG_SERVER_HOST null
ENTRYPOINT ["java","-jar","/app.jar"]