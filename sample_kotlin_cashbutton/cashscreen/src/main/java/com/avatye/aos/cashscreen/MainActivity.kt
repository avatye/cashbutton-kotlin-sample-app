package com.avatye.aos.cashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.avatye.aos.cashscreen.java.CashButtonActivityForJava
import com.avatye.aos.cashscreen.java.CashButtonChannelingActivityForJava
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.core.entity.base.SDKType

class MainActivity : AppCompatActivity() {

    companion object {
        var startJavaSample = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent: Intent

        Handler(mainLooper).postDelayed({
            intent = if (CashButtonConfig.sdkType == SDKType.BUTTON) {
                if (startJavaSample) {
                    Intent(this@MainActivity, CashButtonActivityForJava::class.java)
                } else {
                    Intent(this@MainActivity, CashButtonActivity::class.java)
                }
            } else {
                if (startJavaSample) {
                    Intent(this@MainActivity, CashButtonChannelingActivityForJava::class.java)
                } else {
                    Intent(this@MainActivity, CashButtonChannelingActivity::class.java)
                }
            }

            startActivity(intent)
        }, 500)
    }

}