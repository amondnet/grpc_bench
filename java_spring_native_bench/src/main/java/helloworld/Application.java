package helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private Server server;
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = ServerBuilder.forPort(port)
            .addService(ProtoReflectionService.newInstance())
            .addService(new GreetingImpl()).build().start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM
                // shutdown hook.
                System.err.println(
                    "*** shutting down gRPC server since JVM is shutting down");
                Application.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        final Application server = new Application();
        server.start();
        server.blockUntilShutdown();
    }
}
class GreetingImpl extends GreeterGrpc.GreeterImplBase { // <1>

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        // <3>
        final var reply = HelloReply.newBuilder().setResponse(request.getRequest()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
