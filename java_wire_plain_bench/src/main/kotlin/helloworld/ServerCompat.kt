package helloworld

import io.grpc.ServerBuilder

fun main() {
  val server = ServerBuilder.forPort(50051)
    .addService(GreeterCompatImpl())
    .build()

  server.start()
  server.awaitTermination()
}
