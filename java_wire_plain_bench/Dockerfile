FROM adoptopenjdk/openjdk11:ubi

WORKDIR /app

COPY java_wire_plain_bench /app
COPY proto /app/src/main/proto
ADD /app/helloworld.proto /app/src/main/proto

RUN /app/gradlw build


ENTRYPOINT java -jar /app/build/libs/java_wire_plain_bench.jar
