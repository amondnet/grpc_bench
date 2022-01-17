package io.grpc.examples.helloworld

import io.grpc.ServerBuilder
import io.grpc.examples.helloworld.GreeterCompatImpl

fun main() {
  val server = ServerBuilder.forPort(50051)
    .addService(GreeterCompatImpl())
    .build()

  server.start()
  server.awaitTermination()
}
