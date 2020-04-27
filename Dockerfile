FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/movie-demo-0.0.1-SNAPSHOT.jar movie-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","movie-demo-0.0.1-SNAPSHOT.jar"]
