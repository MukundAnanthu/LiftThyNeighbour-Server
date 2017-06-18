FROM openjdk:8-jdk
RUN apt-get update && apt-get install -y make maven

COPY . /src
WORKDIR /src
RUN make package

CMD ["java", "-jar", "/src/target/neighbour-server-1.0.0.jar"]
