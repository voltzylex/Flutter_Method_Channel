package com.example.method_channel_demo

import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.example.methodchannel/native"
    private lateinit var methodChannelHandler:MethodChannelHandler;
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {

        super.configureFlutterEngine(flutterEngine)
    MethodChannelHandler(this,flutterEngine);

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Forward the result to MethodChannelHandler
        methodChannelHandler.onActivityResult(requestCode, resultCode, data)
    }
}
