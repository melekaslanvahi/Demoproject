FROM adoptopenjdk/openjdk11

COPY ./target/payment-1.0-SNAPSHOT.jar /usr

WORKDIR /usr

RUN sh -c 'touch payment-1.0-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","payment-1.0-SNAPSHOT.jar"]


