FROM adoptopenjdk/openjdk11:ubi

WORKDIR /app

COPY java_quarkus_bench /app
COPY proto /app/src/main/proto

RUN /app/mvnw clean package -Pnative

# Configure the JAVA_OPTIONS, you can add -XshowSettings:vm to also display the heap size.
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

ENTRYPOINT /app/target/java_quarkus_bench-1.0.0-SNAPSHOT-runner -Dquarkus.grpc.server.instances="${GRPC_SERVER_CPUS:-1}"
