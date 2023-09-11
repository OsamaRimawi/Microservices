FROM eclipse-temurin:20.0.2_9-jre-jammy
VOLUME /tmp
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]