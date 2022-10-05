package com.avatye.app.sample.kotlin_cashbutton_sample

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.*
import com.avatye.sdk.cashbutton.ui.CashButtonLayout
import kotlinx.android.synthetic.main.activity_main.*

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
        ly_inquire_container.setOnClickListener {
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

        component_header_view.setBack { onBackPressed() }
    }

}