import 'package:flutter/material.dart';
import 'package:method_channel_demo/screens/activity_channel_view.dart';
import 'package:method_channel_demo/screens/method_channel_data.dart';
import 'package:method_channel_demo/screens/native_view.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(primarySwatch: Colors.teal),
      home: const HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Task Navigation')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TaskButton(title: "New Activity", page: NewActivityDemo()),
            TaskButton(title: "Native View", page: NativeView()),
            TaskButton(
                title: "Task 3", page: const TaskScreen(title: "Task 3")),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                TaskButton(
                    title: "Method Channel Demo",
                    page: const MethodChannelData()),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class TaskButton extends StatelessWidget {
  final String title;
  final Widget page;

  const TaskButton({super.key, required this.title, required this.page});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0),
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          padding: const EdgeInsets.symmetric(vertical: 14),
          shape:
              RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
        ),
        onPressed: () => Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => page),
        ),
        child: Text(title, style: const TextStyle(fontSize: 18)),
      ),
    );
  }
}

class TaskScreen extends StatelessWidget {
  final String title;
  const TaskScreen({super.key, required this.title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(title)),
      body: Center(
        child: Text("This is $title", style: const TextStyle(fontSize: 22)),
      ),
    );
  }
}
