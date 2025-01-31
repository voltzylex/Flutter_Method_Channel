package com.example.method_channel_demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val receivedData = intent.getStringExtra("flutterData") ?: "No data received"
        Log.d("SecondActivity", "Received data: $receivedData")  // Log to check received data
        
        val textView = findViewById<TextView>(R.id.textViewData)
        textView.text = "Received from Flutter: $receivedData"

        val buttonSendBack = findViewById<Button>(R.id.buttonSendBack)
        buttonSendBack.setOnClickListener {
            Log.d("SecondActivity", "Sending data back to Flutter")
            val resultIntent = Intent()
            resultIntent.putExtra("androidResponse", "Data modified in Android")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}

