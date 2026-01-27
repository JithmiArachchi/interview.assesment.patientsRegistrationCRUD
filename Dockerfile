FROM openjdk:21-ea-1-jdk

WORKDIR /app

COPY target/interview.assesment-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

