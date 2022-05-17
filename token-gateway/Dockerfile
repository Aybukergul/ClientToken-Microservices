FROM openjdk:11.0.2-jre-slim
COPY build/libs/token-gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:postgresql://172.17.0.2:5433/db_gateway?currentSchema=sc_product&ssl=false", "-jar", "token-gateway-0.0.1-SNAPSHOT.jar"]