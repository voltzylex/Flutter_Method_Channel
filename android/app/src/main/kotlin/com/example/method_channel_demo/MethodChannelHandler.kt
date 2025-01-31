package com.example.method_channel_demo

import android.content.Intent
import android.app.Activity
import android.util.Log

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
class MethodChannelHandler(private val activity: Activity, flutterEngine: FlutterEngine) {
    private var CHANNEL = "com.example.methodchannel/native"
    private var methodChannelResult: MethodChannel.Result? = null // Store the result here

    init {
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->

            when (call.method) {
                "getMap" -> {
                    val arg = call.arguments<Map<String, String>>()
                    result.success(mapOf(
                        "message" to "Name is ${arg?.get("name") ?: "Unknown"}",
                        "name" to "Sushil",
                        "time" to "Time ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())}"
                    ))
                }

                "getUserGreeting" -> {
                    val arg = call.arguments<String>()
                    val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                    result.success(arg + timestamp)
                }

                "openActivity" -> {
                    // Store the result here
                    methodChannelResult = result
                    val data = call.arguments as? String ?: "Default Data"
                    openSecondActivity(data, result)
                }

                "getMessage" -> {
                    result.success("This is the data from a separate class")
                }

                "getPerson" -> {
                    result.success("This is Sushil")
                }

                else -> {
                    result.notImplemented()
                }
            }
        }
    }

    private fun openSecondActivity(data: String, result: MethodChannel.Result) {
        Log.d("MethodChannel", "openSecondActivity called with data: $data")  // Add logging
        val intent = Intent(activity, SecondActivity::class.java).apply {
            putExtra("flutterData", data)
        }
        activity.startActivityForResult(intent, 1001)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            val responseData = data?.getStringExtra("androidResponse") ?: "No response from Android"

            // Send the response back to Flutter using the stored result
            methodChannelResult?.success(responseData)

            // Clear the result reference after it's used
            methodChannelResult = null
        }
    }
}
