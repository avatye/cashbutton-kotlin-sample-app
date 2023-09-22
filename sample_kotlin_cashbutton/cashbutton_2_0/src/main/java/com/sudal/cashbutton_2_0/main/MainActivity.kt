package com.sudal.cashbutton_2_0.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.core.entity.base.SDKType
import com.sudal.cashbutton_2_0.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.e("asd", "MainActivity -> CashButtonConfig.sdkType: ${CashButtonConfig.sdkType}")
        Handler(mainLooper).postDelayed({
            if (CashButtonConfig.sdkType == SDKType.BUTTON) {
                // 캐시버튼
                startActivity(Intent(this@MainActivity, CashButtonActivity::class.java))
            } else {
                // 캐시버튼 채널링
                startActivity(Intent(this@MainActivity, CashButtonChannelingActivity::class.java))
            }
        }, 500)
    }

}