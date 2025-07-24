FROM bellsoft/liberica-openjdk-alpine:17
VOLUME /tmp
COPY target/companies-crud-fallback-0.0.1-SNAPSHOT.jar app.jar
ENV EUREKA_URL=null
ENTRYPOINT ["java","-jar","/app.jar"]