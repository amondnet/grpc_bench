FROM ghcr.io/graalvm/graalvm-ce:ol8-java11-21.1.0 as rel

WORKDIR /app
COPY java_spring_native_bench /app
COPY proto/helloworld/helloworld.proto /app/src/main/proto/helloworld.proto

RUN /app/gradlew bootBuildImage

#RUN gu install native-image

#RUN rm -rf target/native
#RUN mkdir -p target/native

#RUN cd target/native
#RUN jar -xvf ../my-app-0.0.1-SNAPSHOT.jar >/dev/null 2>&1
#RUN cp -R META-INF BOOT-INF/classes

#RUN native-image --no-server --static --no-fallback -jar /app/build/libs/app-0.1-all.jar

ENTRYPOINT [ "/app/build/hello-world-java" ]
