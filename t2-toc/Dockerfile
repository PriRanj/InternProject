FROM openjdk:11
ADD target/toc-service.jar app.jar
EXPOSE 8300
ENTRYPOINT ["java","-jar","app.jar"]