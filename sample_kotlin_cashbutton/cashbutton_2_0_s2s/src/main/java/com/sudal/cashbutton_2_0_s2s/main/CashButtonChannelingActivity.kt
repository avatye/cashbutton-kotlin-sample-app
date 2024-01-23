package com.sudal.cashbutton_2_0_s2s.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashbutton.product.button.LaunchChannelingBuilder
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.core.widget.FloatingButtonLayout
import com.avatye.sdk.cashbutton.launcher.entity.ChannelingUser
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchButtonListener
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchCustomViewListener
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchFloatingListener
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchViewListener
import com.avatye.sdk.cashbutton.ui.CashButtonLayout
import com.sudal.cashbutton_2_0_s2s.PerfRepository
import com.sudal.cashbutton_2_0_s2s.databinding.ActivityCashButtonChannelingBinding

class CashButtonChannelingActivity : AppCompatActivity() {

    private val binding: ActivityCashButtonChannelingBinding by lazy {
        ActivityCashButtonChannelingBinding.inflate(layoutInflater)
    }

    private var cashButtonLayout: CashButtonLayout? = null
    private var floatingButtonLayout: FloatingButtonLayout? = null
    private var customViewCashButtonLayout: CashButtonLayout? = null

    override fun onBackPressed() {
        cashButtonLayout?.onBackPressed(object : ICashButtonBackPressedListener {
            override fun onBackPressed(isSuccess: Boolean) {
                if (isSuccess) {
                    finish()
                }
            }
        }) ?: run {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /**
         * Channeling Builder Test
         * launchFloating : 전체화면 형태
         * launchButton : 대시보드 형태
         */
        val channelingUser = ChannelingUser(userID = PerfRepository.userid, phoneNumber = PerfRepository.userid + "-12345", nickname = "nick_" + PerfRepository.userid)
        val builder = LaunchChannelingBuilder.Builder(context = this@CashButtonChannelingActivity, channelingUser = channelingUser)
        builder.build(listener = object : LaunchChannelingBuilder.IBuilderListener {
            override fun onSuccess(builder: LaunchChannelingBuilder) {
                Log.e("ChannelBuilder", "LaunchChannelingBuilder::onSuccess")

                launchFloating(builder = builder)
//                launchButton(builder = builder)

                binding.launchActivity.setOnClickListener {
                    launchView(builder = builder)
                }

                binding.launchCustom.setOnClickListener {
                    launchCustomView(builder = builder)
                }
            }

            override fun onFailure(reason: String) {
                Log.e("ChannelBuilder", "LaunchChannelingBuilder::onFailure '$reason'")
            }
        })
    }


    private fun launchFloating(builder: LaunchChannelingBuilder) {
        builder.launchFloating(
            ownerActivity = this@CashButtonChannelingActivity,
            listener = object : ILaunchFloatingListener {
                override fun onSuccess(view: FloatingButtonLayout?) {
                    Log.e("ChannelBuilder", "ILaunchFloatingListener::onSuccess::onSuccess")
                }

                override fun onFailure(reason: String) {
                    Log.e("ChannelBuilder", "ILaunchFloatingListener::onFailure '$reason'")
                }
            }
        )
    }


    private fun launchButton(builder: LaunchChannelingBuilder) {
        builder.launchButton(
            ownerActivity = this@CashButtonChannelingActivity,
            listener = object : ILaunchButtonListener {
                override fun onSuccess(view: CashButtonLayout?) {
                    Log.e("ChannelBuilder", "ILaunchButtonListener::onSuccess::onSuccess")
                    cashButtonLayout = view
                }

                override fun onFailure(reason: String) {
                    Log.e("ChannelBuilder", "ILaunchButtonListener::onFailure '$reason'")
                }
            }
        )
    }

    private fun launchCustomView(builder: LaunchChannelingBuilder) {
        builder.launchCustomView(
            ownerActivity = this@CashButtonChannelingActivity,
            customView = binding.launchCustom,
            listener = object : ILaunchCustomViewListener {
                override fun onSuccess(view: CashButtonLayout?) {
                    customViewCashButtonLayout = view
                    Log.e("ChannelBuilder", "ILaunchCustomViewListener::onSuccess")
                }

                override fun onFailure(reason: String) {
                    Log.e("ChannelBuilder", "ILaunchCustomViewListener::onFailure '$reason'")
                }

                override fun onCoinUpdate(coinValue: String) {
                    binding.launchCustom.text = "Coin $coinValue"
                }
            }
        )
    }

    private fun launchView(builder: LaunchChannelingBuilder) {
        builder.launchView(
            ownerActivity = this@CashButtonChannelingActivity,
            listener = object : ILaunchViewListener {
                override fun onSuccess() {
                    Log.e("ChannelBuilder", "launchView::onSuccess")
                }

                override fun onFailure() {
                    Log.e("ChannelBuilder", "launchView::onFailure")
                }
            }
        )
    }

}