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


        /** 캐시버튼 실행 시 바로 버튼 노출 */
        initCashButton()


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

    private fun initCashButton() {
        CashButtonLayout.init(this, object : ICashButtonCallback {
            override fun onSuccess(view: CashButtonLayout?) {
                cashButtonLayout = view
            }

        }, false)
    }


    private fun setCashButtonCallback() {
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