# syntax=docker/dockerfile:1

FROM eclipse-temurin:11-jre-focal
WORKDIR /app
COPY ./build/libs/mower.jar .
CMD ["java", "-jar", "mower.jar"]
