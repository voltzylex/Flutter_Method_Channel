import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:permission_handler/permission_handler.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatefulWidget {
  const MainApp({super.key});

  @override
  State<MainApp> createState() => _MainAppState();
}

class _MainAppState extends State<MainApp> {
  static const platform = MethodChannel('batteryLevel');

  static const color = MethodChannel("method/color");
  // Get battery level.
  String _batteryLevel = 'Unknown battery level.';
  String _color = "0xffFB0000";

  Future<void> _getBatteryLevel() async {
    String batteryLevel;
    String c;
    try {
      final result = await platform.invokeMethod<int>('getBatteryLevel');
      final colo = await color.invokeMethod<String>("getColor");

      c = colo ?? "0xffFB0000";
      batteryLevel = 'Battery level at $result % .';
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get battery level: '${e.message}'.";
      c = "0xffFB0000";
    }

    setState(() {
      _batteryLevel = batteryLevel;
      _color = c;
    });

    final ob = await color.invokeMethod<Map>("getObject");
    log("Object is ${ob?["name"]}");
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _getBatteryLevel();
    Permission.notification.request();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Color(int.parse(_color)),
        body: Center(
          child: Text('Hello World $_batteryLevel'),
        ),
      ),
    );
  }
}
