package com.avatye.aos.cashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.core.entity.base.SDKType

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(mainLooper).postDelayed({
            if (CashButtonConfig.sdkType == SDKType.BUTTON) {
                startActivity(Intent(this@MainActivity, CashButtonActivity::class.java))
            } else {
                startActivity(Intent(this@MainActivity, CashButtonChannelingActivity::class.java))
            }
        }, 500)
    }

}