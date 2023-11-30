package com.sudal.cashbutton_skp.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashbutton.product.button.LaunchChannelingBuilder
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchViewListener
import com.sudal.cashbutton_skp.PerfRepository
import com.sudal.cashbutton_skp.databinding.ActivityCashButtonChannelingBinding

class CashButtonChannelingActivity : AppCompatActivity() {

    private val binding: ActivityCashButtonChannelingBinding by lazy {
        ActivityCashButtonChannelingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /**
         * Channeling Builder Test
         * launchFloating : 전체화면 형태
         * launchButton : 대시보드 형태
         */
        val builder = LaunchChannelingBuilder.Builder(context = this@CashButtonChannelingActivity, userID = PerfRepository.userid)
        builder.build(listener = object : LaunchChannelingBuilder.IBuilderListener {
            override fun onCompleted(builder: LaunchChannelingBuilder) {
                Log.e("ChannelBuilder", "LaunchChannelingBuilder::onCompleted")

                binding.launchActivity.setOnClickListener {
                    launchView(builder = builder)
                }
            }
        })
    }

    private fun launchView(builder: LaunchChannelingBuilder) {
        builder.launchView(
            ownerActivity = this@CashButtonChannelingActivity,
            listener = object : ILaunchViewListener {
                override fun onLaunched(resultCode: Int) {
                    Log.e("ChannelBuilder", "launchView::resultCode: $resultCode")
                }
            }
        )
    }

}