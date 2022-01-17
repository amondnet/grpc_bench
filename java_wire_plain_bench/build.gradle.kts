plugins {
  application
  java
  kotlin("jvm") version "1.6.0"
  id("com.squareup.wire") version "4.0.1"
  id("com.github.johnrengelman.shadow") version "7.0.0"

}
repositories {
  mavenCentral()
}
application {
  mainClass.set("helloworld.ServerKt")
}
//mainClassName = "helloworld.ServerKt"

wire {
  sourcePath {
    srcDir("src/main/proto")
  }
  kotlin {
    rpcCallStyle = "blocking"
    rpcRole = "server"
    singleMethodServices = true
    grpcServerCompatible = true

  }
}

dependencies {
  implementation("com.squareup.wire:wire-grpc-server:4.0.1")
  implementation("com.squareup.wire:wire-runtime:4.0.1")
  implementation("io.grpc:grpc-netty:1.38.1")
  implementation("io.grpc:grpc-stub:1.38.1")

}

tasks.withType<Jar> {
  manifest {
    attributes["Main-Class"] = "helloworld.ServerKt"
  }
}
