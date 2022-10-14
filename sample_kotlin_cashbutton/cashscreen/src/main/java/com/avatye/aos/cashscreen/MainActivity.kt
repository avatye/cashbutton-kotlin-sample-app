package com.avatye.aos.cashscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.AvatyeSDK
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.ICashButtonCallback
import com.avatye.sdk.cashbutton.core.cashscreen.CashScreenConfig
import com.avatye.sdk.cashbutton.core.cashscreen.ICashScreenCallback
import com.avatye.sdk.cashbutton.core.entity.cashmore.AvatyeUserData
import com.avatye.sdk.cashbutton.ui.CashButtonLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.StartCashButton).setOnClickListener {
            startActivity(Intent(this@MainActivity, CashButtonAcivity::class.java))
        }

        findViewById<Button>(R.id.StartCashButtonChanneling).setOnClickListener {
            startActivity(Intent(this@MainActivity, CashButtonChannelingActivity::class.java))
        }

    }

}