FROM ghcr.io/graalvm/graalvm-ce:ol8-java11-21.1.0 as rel

WORKDIR /app
COPY java_spring_native_bench /app
COPY proto/helloworld/helloworld.proto /app/src/main/proto/helloworld.proto

RUN /app/gradlew bootJar --no-daemon

RUN gu install native-image

RUN rm -rf build/native
RUN mkdir -p build/native

RUN cd build/native
RUN jar -xvf ../libs/java_spring_native_bench-0.1.jar >/dev/null 2>&1
RUN cp -R META-INF BOOT-INF/classes

#RUN native-image --no-server --static --no-fallback -jar /app/build/libs/app-0.1-all.jar
RUN native-image -H:Name=my-app -cp BOOT-INF/classes:`find BOOT-INF/lib | tr '\n' ':'`

ENTRYPOINT [ "/app/build/native/hello-world-java" ]
