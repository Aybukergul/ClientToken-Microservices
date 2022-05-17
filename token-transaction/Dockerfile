FROM openjdk:11.0.2-jre-slim
COPY build/libs/token-transaction-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/db_product?currentSchema=transaction&ssl=false", "-jar", "token-gateway-0.0.1-SNAPSHOT.jar"]