FROM adoptopenjdk/openjdk11

COPY ./target/xprem-1.0-SNAPSHOT.jar /usr

WORKDIR /usr

RUN sh -c 'touch xprem-1.0-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","xprem-1.0-SNAPSHOT.jar"]


