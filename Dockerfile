FROM maven:3-jdk-11 as build
COPY  . /app/src
WORKDIR /app/src
RUN mvn clean package

FROM openjdk:11.0.5-jre
WORKDIR /app
COPY --from=build /app/src/target/shop.jar /app
ENTRYPOINT ["java","-jar","/app/shop.jar"]
