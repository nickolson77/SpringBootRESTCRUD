FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/demo-0.0.1.jar /app/demo-0.0.1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "demo-0.0.1.jar"]

# 1. docker build -t demo .
# 2. docker run -p 8080:8080 demo demo
