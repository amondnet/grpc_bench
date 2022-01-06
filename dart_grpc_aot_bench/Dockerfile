FROM dart:stable AS build

WORKDIR /app
COPY dart_grpc_bench/pubspec.yaml /app/pubspec.yaml
COPY proto /app/proto

RUN pub get

COPY dart_grpc_bench/bin /app/bin
# Ensure packages are still up-to-date if anything has changed
RUN pub get --offline
RUN apt-get update && apt-get install -y protobuf-compiler
RUN pub global activate protoc_plugin
ENV PATH="${HOME}/.pub-cache/bin:${PATH}"
RUN mkdir -p lib/src/generated
RUN protoc --proto_path=/app/proto/helloworld --dart_out=grpc:lib/src/generated -Iprotos /app/proto/helloworld/helloworld.proto --plugin=protoc-gen-dart=$HOME/.pub-cache/bin/protoc-gen-dart

RUN dart compile exe bin/server.dart -o bin/server


# Build minimal serving image from AOT-compiled `/server` and required system
# libraries and configuration files stored in `/runtime/` from the build stage.
FROM scratch
COPY --from=build /runtime/ /
COPY --from=build /app/bin/server /app/bin/

# Start server.
EXPOSE 50051
CMD ["/app/bin/server"]