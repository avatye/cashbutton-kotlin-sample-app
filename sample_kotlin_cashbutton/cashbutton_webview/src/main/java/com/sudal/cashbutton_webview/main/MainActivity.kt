package com.sudal.cashbutton_webview.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.core.entity.base.SDKType
import com.sudal.cashbutton_webview.main.java.CashButtonJavaActivity

class MainActivity : AppCompatActivity() {

    /**
     * 코틀린 or 자바 (언어선택)
     * */
    private val useKotlin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("asd", "MainActivity -> CashButtonConfig.sdkType: ${CashButtonConfig.sdkType}")

        Handler(Looper.getMainLooper()).postDelayed({
            when (CashButtonConfig.sdkType) {
                // 캐시버튼
                SDKType.BUTTON, SDKType.NONE -> {
                    if (useKotlin) {
                        startActivity(Intent(this@MainActivity, CashButtonKotlinActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@MainActivity, CashButtonJavaActivity::class.java))
                        finish()
                    }
                }

                // 캐시버튼 채널링
                SDKType.CHANNELING, SDKType.CHANNELING_DIRECT, SDKType.CHANNELING_DIRECT2 -> {
                    startActivity(Intent(this@MainActivity, CashButtonChannelingActivity::class.java))
                    finish()
                }
            }
        }, 1000 * 1)
    }

}