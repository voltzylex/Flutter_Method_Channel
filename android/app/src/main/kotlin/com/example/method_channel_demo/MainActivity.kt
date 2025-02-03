package com.example.method_channel_demo

import android.content.Intent
import com.example.nativetextviewdemo.NativeComplexViewFactory
import com.example.nativetextviewdemo.NativeTextViewFactory
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.example.methodchannel/native"
    private lateinit var methodChannelHandler:MethodChannelHandler;
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {

        super.configureFlutterEngine(flutterEngine)
    MethodChannelHandler(this,flutterEngine);
        // Register the native view with an arbitrary string identifier.
        flutterEngine
            .platformViewsController
            .registry
            .registerViewFactory("native-text-view", NativeTextViewFactory())
        flutterEngine.platformViewsController.registry.registerViewFactory("native-complex-view",NativeComplexViewFactory())

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Forward the result to MethodChannelHandler
        methodChannelHandler.onActivityResult(requestCode, resultCode, data)
    }
}
