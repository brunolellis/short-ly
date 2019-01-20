FROM openjdk:11-jre-slim

ADD target/shortly-api.jar shortly-api.jar

ENTRYPOINT ["java","-jar","shortly-api.jar"]
