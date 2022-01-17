package helloworld


import io.grpc.stub.StreamObserver
import java.util.concurrent.Executors

class GreeterCompatImpl : GreeterWireGrpc.GreeterImplBase() {
  override fun SayHello(request: HelloRequest, response: StreamObserver<HelloReply>) {

    GreeterWireGrpc.GreeterImplLegacyAdapter(
      SayHello = { GreeterCompat() },
      streamExecutor = Executors.newSingleThreadExecutor()
    )
      .SayHello(request, response)
  }
}

class GreeterCompat : GreeterSayHelloBlockingServer {
  override fun SayHello(request: HelloRequest): HelloReply {
    return HelloReply(request.request)
  }
}
