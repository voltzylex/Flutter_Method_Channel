import 'package:battery_level/MethodView/basic_method.dart';
import 'package:flutter/material.dart';

class AndroidNativeView extends StatefulWidget {
  const AndroidNativeView({super.key});

  @override
  _AndroidNativeViewState createState() => _AndroidNativeViewState();
}

class _AndroidNativeViewState extends State<AndroidNativeView> {
  final method = BasicMethod();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Native View Example')),
      body: Container(
        color: method.color,
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () {
                  method.changeColor();
                  setState(() {});
                },
                child: const Text('Change Color'),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  method.getBatteryLevel();
                  setState(() {});
                },
                child: const Text('Get Battery Level'),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  method.getWelcomeString();
                  setState(() {});
                },
                child: const Text('Get String'),
              ),
              const SizedBox(height: 20),
              Text(
                method.result,
                style: const TextStyle(fontSize: 20),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
