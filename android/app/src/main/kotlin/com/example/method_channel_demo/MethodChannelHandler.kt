package com.example.method_channel_demo

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MethodChannelHandler(flutterEngine: FlutterEngine) {
    private var CHANNEL = "com.example.methodchannel/native";

    init {
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->

            when (call.method) {
                "getMap" -> {
                    val arg = call.arguments<Map<String, String>>();
                    result.success(mapOf( "message" to "Name is ${arg?.get("name") ?:"Unknown"}",
                        "name" to "Sushil",
                        "time" to "Time ${ SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())}"
                        ))

                }

                "getUserGreeting" -> {
                    val arg = call.arguments<String>();
                    val timestamp =
                        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date());
                    result.success(arg + timestamp)
                }

                "getMessage" -> {
                    result.success("This is the data from separate class");
                }

                "getPerson" -> {
                    result.success("this is sushil")
                }

                else -> {
                    result.notImplemented()
                }
            }
        }

    }
}