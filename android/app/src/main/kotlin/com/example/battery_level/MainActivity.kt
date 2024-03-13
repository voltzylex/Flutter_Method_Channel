package com.example.battery_level

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel


class MainActivity : FlutterActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getBatteryLevel(context: Context): Int {

        val batteryManager = context.getSystemService(BATTERY_SERVICE) as BatteryManager

        return batteryManager.getIntProperty(/* id = */ BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }




    private val CHANNEL = "batteryLevel";
    private val methodColor = "method/color";
    private val backgroundMedia = "background/media";

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {

        super.configureFlutterEngine(flutterEngine)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            JobSchedulerClass.scheduleJob(this)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_NOTIFICATION_POLICY
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_NOTIFICATION_POLICY),
                    101
                )
            }
        }
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            backgroundMedia
        ).setMethodCallHandler { call, result ->
            if (call.method == "getStorage") {
                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
               var savedValue = sharedPreferences.getString("myKey", "DefaultValueIfKeyNotFound");
                result.success(
                    savedValue
                );
            }else{
                result.notImplemented()
            }
            }


            MethodChannel(
                flutterEngine.dartExecutor.binaryMessenger,
                CHANNEL
            ).setMethodCallHandler { call, result ->
                if (call.method == "getBatteryLevel") {
                    val batteryLevel = getBatteryLevel(this)

                    if (batteryLevel != -1) {
                        result.success(batteryLevel)
                    } else {
                        result.error("UNAVAILABLE", "Battery level not available.", null)
                    }
                } else {
                    result.notImplemented()
                }
            }
            MethodChannel(
                flutterEngine.dartExecutor.binaryMessenger,
                methodColor
            ).setMethodCallHandler { call, result ->
                if (call.method == "getColor") {
                    result.success("0xffFE8C00");
                }
                if (call.method == "getObject") {
                    val resultMap: MutableMap<String, Any> = HashMap()
                    resultMap["success"] = false
                    resultMap["name"] = "nitesh sir"
                    result.success(
                        resultMap
//                    hashMapOf(
//                        "success" to true,
//                        "name" to "manish"
//                    )
                    );
                } else {
                    result.success("0xff53B781");
                }

            }

        }
    }



