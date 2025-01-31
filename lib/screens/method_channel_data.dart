
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class MethodChannelData extends StatefulWidget {
  const MethodChannelData({super.key});

  @override
  State<MethodChannelData> createState() => _MethodChannelDataState();
}

class _MethodChannelDataState extends State<MethodChannelData> {
  static const platform = MethodChannel('com.example.methodchannel/native');

  final ValueNotifier<String> messageNotifier =
      ValueNotifier<String>("Click the button to get a message from Android");

  Future<void> getMessageFromNative() async {
    try {
      final String result = await platform.invokeMethod('getMessage');
      messageNotifier.value = result;
    } on PlatformException catch (e) {
      messageNotifier.value = "Failed to get message: '${e.message}'";
    }
  }

  Future<void> getPerson() async {
    try {
      final String result = await platform.invokeMethod('getPerson');
      messageNotifier.value = result;
    } on PlatformException catch (e) {
      messageNotifier.value = "Failed to get person: '$e'";
    }
  }

  Future<void> sendName(String name) async {
    try {
      final String result =
          await platform.invokeMethod("getUserGreeting", name);
      messageNotifier.value = result;
    } on PlatformException catch (e) {
      messageNotifier.value = "Failed to send name: '$e'";
    }
  }

  Future<void> sendMapName(String name) async {
    try {
      final Map<String, String> args = {"name": name};
      final result = await platform.invokeMethod("getMap", args);
      final res = result as Map;
      messageNotifier.value = res.toString();
    } on PlatformException catch (e) {
      messageNotifier.value = "Failed to send map: '$e'";
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
              onPressed: getMessageFromNative,
              icon: const Icon(Icons.message),
              label: const Text('Get Message'),
            ),
            const SizedBox(height: 10),
            ElevatedButton.icon(
              onPressed: getPerson,
              icon: const Icon(Icons.person),
              label: const Text('Get Person'),
            ),
            const SizedBox(height: 10),
            TextField(
              onSubmitted: sendName,
              decoration: InputDecoration(
                hintText: "Enter name",
                prefixIcon: const Icon(Icons.person_outline),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
              ),
            ),
            const SizedBox(height: 10),
            ElevatedButton.icon(
              onPressed: () => sendMapName("Sushil"),
              icon: const Icon(Icons.map),
              label: const Text('Send Map Data'),
            ),
          ],
        ),
      ),
    );
  }
}
