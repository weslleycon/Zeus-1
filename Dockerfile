FROM maven:3.8.6-openjdk-8 AS builder
WORKDIR /app
ADD src src
ADD pom.xml .
RUN mvn clean package -Dmaven.repo.local=.m2/repository -DskipTests=false

FROM openjdk:8-jre-alpine

ARG PROFILE

ENV SPRING_PROFILES_ACTIVE=$PROFILE
RUN echo "PROFILE_ENV: $PROFILE"

ENV LANG=pt_BR.UTF-8
WORKDIR /app
VOLUME /tmp
COPY --from=builder /app/target/*.jar app.jar

RUN apk add --no-cache tzdata
ENV TZ America/Fortaleza
RUN date

ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 9070