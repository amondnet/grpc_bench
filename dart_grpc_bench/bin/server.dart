// Copyright (c) 2018, the gRPC project authors. Please see the AUTHORS file
// for details. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/// Dart implementation of the gRPC helloworld.Greeter server.
import 'dart:io';

import 'package:grpc/grpc.dart';

import 'package:helloworld/src/generated/helloworld.pb.dart';
import 'package:helloworld/src/generated/helloworld.pbgrpc.dart';

class GreeterService extends GreeterServiceBase {
  @override
  Future<HelloReply> sayHello(ServiceCall call, HelloRequest request) async {
    return HelloReply()..message = '${request.name}';
  }
}

Future<void> main(List<String> args) async {
  Map<String, String> env = Platform.environment;

  final server = Server([GreeterService()]);

  final cups = int.tryParse(env["GRPC_SERVER_CPUS"] ?? '1') ?? 1;

  for (var serve = 0; serve < cups; serve++) {
    await server.serve(address: '0.0.0.0', port: 50051, shared: true);
  }

  // await server.serve(address: '0.0.0.0', port: 50051, shared: true);
  print('Server listening on port ${server.port}...');
}
