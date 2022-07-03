# Builder stage 
FROM maven as builder
COPY ./ ./opt
WORKDIR /opt
RUN mvn clean install 

#Final stage
FROM openjdk:11 as final
WORKDIR ./
COPY --from=builder /opt/target/restproject-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
