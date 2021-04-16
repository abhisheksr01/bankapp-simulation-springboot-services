FROM openjdk:8-jre-alpine
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
ARG APPJAR=build/libs/transaction-0.0.1-SNAPSHOT.jar
COPY ${APPJAR} transaction-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java $JAVA_OPTS -jar transaction-0.0.1-SNAPSHOT.jar