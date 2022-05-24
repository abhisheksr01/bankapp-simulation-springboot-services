FROM openjdk:18.0-jdk
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
ARG APPJAR=build/libs/balance-0.0.1-SNAPSHOT.jar
COPY ${APPJAR} balance-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java $JAVA_OPTS -jar balance-0.0.1-SNAPSHOT.jar