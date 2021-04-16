FROM openjdk:8-jre-alpine
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
ARG APPJAR=build/libs/account-0.0.1-SNAPSHOT.jar
COPY ${APPJAR} account-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java $JAVA_OPTS -jar account-0.0.1-SNAPSHOT.jar