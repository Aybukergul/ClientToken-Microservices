FROM openjdk:11.0.2-jre-slim
COPY build/libs/token-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:postgresql://postgres:5432/db_product?currentSchema=sc_product&ssl=false", "-jar", "token-0.0.1-SNAPSHOT.jar"]