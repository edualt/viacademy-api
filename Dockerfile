FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ ./src/

RUN mvn clean package -DskipTests=true

CMD ["java", "-jar", "target/viacademy-api.jar"]