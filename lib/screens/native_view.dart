import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class NativeView extends StatelessWidget {
  const NativeView({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Native Android TextView'),
      ),
      body: Column(
        children: [
          Center(
            child: SizedBox(
              height: 100,
              width: 300,
              child: AndroidView(
                viewType: 'native-text-view', // Must match the registered id.
                // Optionally, you can pass parameters.
                creationParams: <String, dynamic>{},
                creationParamsCodec: const StandardMessageCodec(),
              ),
            ),
          ),
          Center(
            child: SizedBox(
              height: 100,
              width: 300,
              child: AndroidView(
                viewType:
                    'native-complex-view', // Must match the registered id.
                // Optionally, you can pass parameters.
                creationParams: <String, dynamic>{},
                creationParamsCodec: const StandardMessageCodec(),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
