FROM openjdk:11
ADD target/trainer-service.jar app.jar
EXPOSE 8200
ENTRYPOINT ["java","-jar","app.jar"]