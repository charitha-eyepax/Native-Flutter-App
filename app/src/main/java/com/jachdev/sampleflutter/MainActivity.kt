package com.jachdev.sampleflutter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlin.math.log
import kotlin.math.log10

class MainActivity : FlutterActivity() {

    private val CHANNEL = "com.jachdev.flutter_module.host/login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener(
                View.OnClickListener {
                    startActivity(
                            FlutterActivity
                                    .withCachedEngine("my_engine_id")
                                    .build(this)
                    )
                }
        )
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
            if (call.method == "login") {
                val login = login()

                if (0 == login) {
                    result.success(login)
                } else {
                    result.error("UNAVAILABLE", "Login error.", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }

    private fun login(): Int {

        Toast.makeText(context, "Call login api from native app.", Toast.LENGTH_SHORT).show()

        return 0;
    }
}
