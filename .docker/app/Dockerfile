FROM openjdk:17-jdk

WORKDIR /app
RUN mkdir "/data"
COPY ./.docker/app/examples/ /data/

COPY target/table-data-dashboard-0.0.1-SNAPSHOT.jar table-data-dashboard.jar
CMD ["java", "-jar", "-Xmx512m", "/app/table-data-dashboard.jar"]