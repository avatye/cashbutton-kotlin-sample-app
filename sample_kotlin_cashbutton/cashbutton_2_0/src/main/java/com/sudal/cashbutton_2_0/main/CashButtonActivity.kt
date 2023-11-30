package com.sudal.cashbutton_2_0.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashbutton.product.button.LaunchButtonBuilder
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchButtonListener
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchCustomViewListener
import com.avatye.sdk.cashbutton.ui.CashButtonLayout
import com.sudal.cashbutton_2_0.databinding.ActivityCashButtonBinding

class CashButtonActivity : AppCompatActivity() {

    private var cashButtonLayout: CashButtonLayout? = null

    private val binding: ActivityCashButtonBinding by lazy {
        ActivityCashButtonBinding.inflate(layoutInflater)
    }


    override fun onBackPressed() {
        cashButtonLayout?.onBackPressed(object : ICashButtonBackPressedListener {
            override fun onBackPressed(isSuccess: Boolean) {
                if (isSuccess) {
                    finish()
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        LaunchButtonBuilder.Builder(
            context = this@CashButtonActivity
        ).build(listener = object : LaunchButtonBuilder.IBuilderListener {
            override fun onSuccess(builder: LaunchButtonBuilder) {
                // button
                launchButton(builder = builder)

                // custom-button
//                launchCustomView(builder = builder)
            }

            override fun onFailure(reason: String) {
            }
        })
    }


    private fun launchButton(builder: LaunchButtonBuilder) {
        builder.launchButton(
            ownerActivity = this@CashButtonActivity,
            useStartWithInVisible = true,
            listener = object : ILaunchButtonListener {
                override fun onSuccess(view: CashButtonLayout?) {
                    cashButtonLayout = view
                    CashButtonConfig.useCustomCashButton = true
                }

                override fun onFailure(reason: String) {
                    Log.e("ChannelBuilder", "launchButton::onFailure '$reason'")
                }

                override fun onDashBoardStateChange(state: CashButtonLayout.State) {
                    super.onDashBoardStateChange(state)
                }
            }
        )

        binding.btShowCashbutton.setOnClickListener {
            cashButtonLayout?.showDashBoard()
        }
    }

    private fun launchCustomView(builder: LaunchButtonBuilder) {
        builder.launchCustomView(
            ownerActivity = this@CashButtonActivity,
            customView = null,
            listener = object : ILaunchCustomViewListener {
                override fun onSuccess(view: CashButtonLayout?) {

                }

                override fun onFailure(reason: String) {
                    Log.e("ChannelBuilder", "launchFloater::onFailure '$reason'")
                }

                override fun onCoinUpdate(coinValue: String) {
                    binding.launchCustom.text = "Coin $coinValue"
                }
            }
        )
    }
}