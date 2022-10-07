package com.avatye.app.sample.kotlin_cashbutton_sample

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.ICashButtonCallback
import com.avatye.sdk.cashbutton.ui.CashButtonLayout

class MainActivity : AppCompatActivity() {

    private var cashButtonLayout: CashButtonLayout? = null

    override fun onBackPressed() {
        cashButtonLayout?.onBackPressed(object : ICashButtonBackPressedListener {
            override fun onBackPressed(isSuccess: Boolean) {
                if (isSuccess) {
                    finish()
                }
            }
        }) ?: run {
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // region {init CashButton}
        initCommon()
        // endregion

        // region { 캐시버튼 문의 }
        findViewById<LinearLayout>(R.id.ly_inquire_container).setOnClickListener {
            CashButtonConfig.actionSuggestion(this)
        }
        // endregion
    }


    private fun initCommon() {
        CashButtonLayout.init(this@MainActivity, object : ICashButtonCallback {
            override fun onSuccess(view: CashButtonLayout?) {
                cashButtonLayout = view
            }
        }, false)

        findViewById<HeaderView>(R.id.component_header_view).setBack { onBackPressed() }
    }

}