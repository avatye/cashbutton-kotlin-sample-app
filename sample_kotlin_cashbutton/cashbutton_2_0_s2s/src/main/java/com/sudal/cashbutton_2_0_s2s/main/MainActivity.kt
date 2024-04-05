package com.sudal.cashbutton_2_0_s2s.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.core.entity.base.SDKType

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("asd", "MainActivity -> CashButtonConfig.sdkType: ${CashButtonConfig.sdkType}")

        Handler(Looper.getMainLooper()).postDelayed({
            when (CashButtonConfig.sdkType) {
                // 캐시버튼
                SDKType.BUTTON -> {
                    startActivity(Intent(this@MainActivity, CashButtonActivity::class.java))
                }

                // 캐시버튼 채널링
                SDKType.CHANNELING, SDKType.CHANNELING_DIRECT, SDKType.CHANNELING_DIRECT2 ->
                    startActivity(Intent(this@MainActivity, CashButtonChannelingActivity::class.java))

                // None
                SDKType.NONE -> {
                    startActivity(Intent(this@MainActivity, CashButtonChannelingActivity::class.java))
                }
            }
        }, 500)
    }
}