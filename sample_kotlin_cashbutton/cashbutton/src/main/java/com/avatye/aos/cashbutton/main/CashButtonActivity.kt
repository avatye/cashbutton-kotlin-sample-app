package com.avatye.aos.cashbutton.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.avatye.aos.cashbutton.R
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.ICashButtonCallback
import com.avatye.sdk.cashbutton.ICashButtonViewStateListener
import com.avatye.sdk.cashbutton.ui.CashButtonLayout

class CashButtonActivity : AppCompatActivity() {

    private var cashButtonLayout: CashButtonLayout? = null


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
        setContentView(R.layout.activity_cash_button)

        /**
         * 커스텀 캐시버튼 사용유무
         * CashButtonConfig.useCustomCashButton = true (Application.kt 에서 선언!!)
         */
        if(CashButtonConfig.useCustomCashButton) {
            initCustomButton()
        } else {
            initCashButton()
        }



        /** 캐시버튼 실행 시 원하는 타이밍에 버튼 노출  */
        // 캐시버튼 콜백 설정
//        setCashButtonCallback()
//        CashButtonConfig.start(this)


        /**
         * 캐시버튼 상태 설정
         * @param useOverlayButton : 인앱 오버레이 기능 사용 유무
         * @param startPositionX : X좌표
         * @param startPositionY : Y좌표
         */
//        CashButtonConfig.setCashButtonOption(false)
    }


    private fun initCustomButton() {
        CashButtonLayout.init(this, object : ICashButtonCallback {
            override fun onSuccess(view: CashButtonLayout?) {
                cashButtonLayout = view
                setCustomCashButton()
            }

        })
    }


    private fun initCashButton() {
        CashButtonLayout.init(this, object : ICashButtonCallback {
            override fun onSuccess(view: CashButtonLayout?) {
                cashButtonLayout = view
            }

        })
    }


    private fun setCashButtonCallback() {
        /** set CashButton-Callback */
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


    private fun setCustomCashButton(){
        val targetView = findViewById<Button>(R.id.CustomCashButton)
        val targetTextView = findViewById<TextView>(R.id.tvCoin)

        cashButtonLayout?.setCustomCashButton(targetView) { coin ->
            Log.e("MainActivity", "MainAcitivity -> coin : $coin")
            targetTextView.text = coin
        }

        targetView.setOnClickListener {
            cashButtonLayout?.showDashBoard()
        }
    }
}