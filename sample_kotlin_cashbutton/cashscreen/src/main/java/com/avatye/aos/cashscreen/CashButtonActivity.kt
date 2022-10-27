package com.avatye.aos.cashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.IButtonCallback
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.ICashButtonCallback
import com.avatye.sdk.cashbutton.core.cashscreen.CashScreenConfig
import com.avatye.sdk.cashbutton.core.cashscreen.ICashScreenCallback
import com.avatye.sdk.cashbutton.core.external.CashButtonEvent
import com.avatye.sdk.cashbutton.core.external.ICashButtonBalanceListener
import com.avatye.sdk.cashbutton.core.widget.FloatingButtonLayout
import com.avatye.sdk.cashbutton.ui.CashButtonLayout

class CashButtonActivity : AppCompatActivity() {

    /** 캐시버튼 일 떄 사용 */
    private var cashButtonLayout: CashButtonLayout? = null

    /** 캐시버튼 이벤트 받을 때 사용 */
    private var balanceEventBuilder: CashButtonEvent.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_button)

        initCashButton()

        initMenuItem()
        initCashScreenCustomButton()

        CashButtonConfig.show(this, object : IButtonCallback {
            override fun onSuccess(view: FloatingButtonLayout?) {

            }
        })
    }

    /** 캐시버튼 기본설정 */
    private fun initCashButton() {

        /** 커스텀 캐시버튼 사용 시 설정 */
        if (CashButtonConfig.useCustomCashButton) {
            initCustomCashButton()
        } else {
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
    }

    /** 커스텀 캐시버튼 사용 시 설정 */
    private fun initCustomCashButton() {
        val customButtonView = findViewById<Button>(R.id.CustomCashButton)
        customButtonView.isEnabled = true

        /** 코인, 캐시, 티켓 이벤트를 이용해 자유롭게 커스텀 가능 */
        balanceEventBuilder = CashButtonEvent.Builder(this)
            .addListener(object : ICashButtonBalanceListener {
                @SuppressLint("SetTextI18n")
                override fun onBalanceUpdate(coin: String, cash: Int, ticket: Int) {
                    customButtonView.text = coin
                }
            }).build()

        customButtonView.setOnClickListener {
            CashButtonConfig.start(this)
        }
    }

    /**
     * 별도의 잠금화면 ON/OFF 설정 필요 시
     * 아래와 같이 커스텀하여 사용가능
     * */
    private fun initCashScreenCustomButton() {
        val screenBtn: Button = findViewById(R.id.CashScreen)

        screenBtn.setOnClickListener {
            if (CashScreenConfig.isOn) {
                CashScreenConfig.stop()
            } else {
                CashScreenConfig.start(this)
            }
        }

        setScreenBtnText()

        CashScreenConfig.setOnCashScreenCallback(object : ICashScreenCallback {
            override fun onStart() {
                setScreenBtnText()
            }

            override fun onStop() {
                setScreenBtnText()
            }
        })
    }

    /**
     * 잠금화면 메뉴 추가
     * 잠금화면에서 앱(파트너사의 어플리케이션)의 다른 회면에 접근 가능하도록 메뉴를 추가하실 수 있습니다.(최대 2개)
     * */
    private fun initMenuItem() {
        CashScreenConfig.setLandingIntent(
            CashScreenConfig.ExtraMenuItem(
                menuIconResID = R.drawable.avt_cm_ir_menu_exchange,
                Intent(this@CashButtonActivity, TestActivity::class.java),
                "테스트1"
            ),
            CashScreenConfig.ExtraMenuItem(
                menuIconResID = R.drawable.avt_cm_ir_menu_accumulate,
                Intent(this@CashButtonActivity, TestActivity2::class.java),
                "테스트2"
            )
        )
    }

    private fun setScreenBtnText() {
        val screenBtn: Button = findViewById(R.id.CashScreen)
        screenBtn.text = "잠금화면 : ${if (CashScreenConfig.isOn) "on" else "off"}"
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

    /** 등록한 이벤트 해제 */
    override fun onDestroy() {
        super.onDestroy()
        balanceEventBuilder?.destroy()
    }
}