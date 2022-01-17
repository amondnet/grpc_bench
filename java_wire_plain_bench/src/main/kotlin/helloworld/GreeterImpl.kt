package helloworld


import io.grpc.stub.StreamObserver

class GreeterImpl : GreeterWireGrpc.GreeterImplBase() {
  override fun SayHello(request: HelloRequest, response: StreamObserver<HelloReply>) {

    response.onNext(HelloReply(
      response = request.request
    ))
    response.onCompleted()

  }
}
