import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class NewActivityDemo extends StatefulWidget {
  const NewActivityDemo({super.key});

  @override
  State<NewActivityDemo> createState() => _NewActivityDemoState();
}

class _NewActivityDemoState extends State<NewActivityDemo> {
  static const platform = MethodChannel('com.example.methodchannel/native');

  final ValueNotifier<String> messageNotifier =
      ValueNotifier<String>("Click the button to get a message from Android");

  Future<void> openNativeActivity() async {
    try {
      final String result =
          await platform.invokeMethod('openActivity', 'Hello from Flutter!');
      messageNotifier.value = result;
    } on PlatformException catch (e) {
      messageNotifier.value = "Failed to open activity: '${e.message}'";
    }
  }

  @override
  void dispose() {
    messageNotifier.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Method Channel Demo')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ValueListenableBuilder<String>(
              valueListenable: messageNotifier,
              builder: (context, message, _) {
                return Text(
                  message,
                  textAlign: TextAlign.center,
                  style: const TextStyle(
                      fontSize: 18, fontWeight: FontWeight.w500),
                );
              },
            ),
            const SizedBox(height: 30),
            ElevatedButton.icon(
              onPressed: openNativeActivity,
              icon: const Icon(Icons.open_in_browser),
              label: const Text('Open Native Activity'),
            ),
          ],
        ),
      ),
    );
  }
}
