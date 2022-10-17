package com.avatye.aos.cashbutton

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.ICashButtonCallback
import com.avatye.sdk.cashbutton.ui.CashButtonLayout

class CashButtonActivity : AppCompatActivity() {

    /** 캐시버튼 일 떄 사용 */
    private var cashButtonLayout: CashButtonLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_button)

        initCashButton()

        CashButtonConfig.start(this)
    }

    /** 캐시버튼 기본설정 */
    private fun initCashButton() {
        /** initialize cashbutton view */
        CashButtonConfig.cashButtonCallback = object : ICashButtonCallback {
            override fun onSuccess(view: CashButtonLayout?) {
                cashButtonLayout = view
            }

            override fun onDashBoardStateChange(state: CashButtonLayout.State) {
                super.onDashBoardStateChange(state)
                Log.e("MainActivity", "MainAcitivity -> onDashBoardStateChange : $state")

            }
        }
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
}