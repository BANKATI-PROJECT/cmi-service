FROM openjdk:17-oracle3
COPY target/*.jar  app.jar
EXPOSE 8099
ENTRYPOINT ["java","-jar", "app.jar"]