package com.example.method_channel_demo

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.example.methodchannel/native"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {

        super.configureFlutterEngine(flutterEngine)
    MethodChannelHandler(flutterEngine);
//        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
//            .setMethodCallHandler { call, result ->
//                if (call.method == "getMessage") {
//                    result.success("Hello from Android Native Code!")
//                } else {
//                    result.notImplemented()
//                }
//            }
    }
}
