FROM dart:stable

WORKDIR /app
COPY dart_grpc_bench/pubspec.yaml /app/pubspec.yaml
COPY proto /app/proto

RUN pub get
COPY dart_grpc_bench /app
RUN pub get --offline
RUN apt-get update && apt-get install -y protobuf-compiler
RUN pub global activate protoc_plugin
ENV PATH="${HOME}/.pub-cache/bin:${PATH}"
RUN mkdir -p lib/src/generated
RUN protoc --proto_path=/app/proto/helloworld --dart_out=grpc:lib/src/generated -Iprotos /app/proto/helloworld/helloworld.proto --plugin=protoc-gen-dart=$HOME/.pub-cache/bin/protoc-gen-dart

EXPOSE 50051

ENTRYPOINT [ "/usr/bin/dart", "/app/bin/server.dart" ]
