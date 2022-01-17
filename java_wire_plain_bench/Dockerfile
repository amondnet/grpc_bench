FROM adoptopenjdk/openjdk11:ubi

WORKDIR /app

COPY java_wire_plain_bench /app
COPY proto /app/src/main/proto
ADD java_wire_plain_bench/helloworld.proto /app/src/main/proto

RUN /app/gradlew build


ENTRYPOINT java -jar /app/build/libs/java_wire_plain_bench.jar
