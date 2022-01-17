package com.example;

import helloworld.HelloReply;
import helloworld.HelloRequest;
import helloworld.MutinyGreeterGrpc;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class HelloService extends MutinyGreeterGrpc.GreeterImplBase {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item(
                HelloReply.newBuilder().setResponse(request.getRequest()).build()
        );
    }
}
