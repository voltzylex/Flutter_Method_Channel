import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class BasicMethod {
  static const platform = MethodChannel('com.example.yourapp/native');
  String result = '';
  Color color = Colors.white; // Default color

  Future<void> changeColor() async {
    try {
      final int colorValue = await platform.invokeMethod('getColor');

      color = Color(colorValue);
    } on PlatformException catch (e) {
      result = 'Failed to change color: ${e.message}';
    }
  }

  Future<void> getBatteryLevel() async {
    try {
      final int batteryLevel = await platform.invokeMethod('getBatteryLevel');

      result = 'Battery Level: $batteryLevel%';
    } on PlatformException catch (e) {
      result = 'Failed to get battery level: ${e.message}';
    }
  }

  Future<void> getWelcomeString() async {
    try {
      final String welcome = await platform.invokeMethod("getString");
      result = welcome;
    } on PlatformException catch (e) {
      result = "Failed to Get String : ${e.message}";
    }
  }

  // Get Version from native Through map
  Future<void> getDeviceInfo() async {
    final info = await platform.invokeMethod("getDeviceInfo");
    print(info);
    print("Info type is ${info.runtimeType}");
  }
}
