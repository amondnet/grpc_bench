FROM adoptopenjdk/openjdk11:ubi

WORKDIR /app

COPY java_wire_plain_bench /app
COPY proto /app/src/main/proto

RUN /app/gradlew installDist

ENTRYPOINT ["/app/build/install/java_wire_plain_bench/bin/java_wire_plain_bench"]
#ENTRYPOINT java -jar /app/build/libs/java_wire_plain_bench.jar
