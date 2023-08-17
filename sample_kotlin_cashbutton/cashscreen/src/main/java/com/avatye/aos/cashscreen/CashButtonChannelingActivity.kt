package com.avatye.aos.cashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.IButtonCallback
import com.avatye.sdk.cashbutton.core.cashscreen.CashScreenConfig
import com.avatye.sdk.cashbutton.core.cashscreen.ICashScreenCallback
import com.avatye.sdk.cashbutton.core.entity.cashmore.AvatyeUserData
import com.avatye.sdk.cashbutton.core.widget.FloatingButtonLayout

class CashButtonChannelingActivity : AppCompatActivity() {

    /** 캐시버튼 채널링 테스트 시 본인 번호 설정 */
    private val phoneNumber = "01077915154"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_button_channeling)

        initCashButtonChanneling()

        initMenuItem()
        initCashScreenCustomButton()

        findViewById<Button>(R.id.CustomCashButton).setOnClickListener {
            CashButtonConfig.start(this)
        }
    }

    /** 캐시버튼 채널링 기본설정 */
    private fun initCashButtonChanneling() {
        val userData = AvatyeUserData(
            userID = "USERID_$phoneNumber", phoneNumber = phoneNumber, nickname = phoneNumber
        )

        CashButtonConfig.userData = userData

        /**
         * 채널링 서비스 이용 시 별도의 진입 점을 이용하지 않고
         * 기본 제공되는 버튼 진입점을 사용시 start를 호출하지 않고 show를 호출한다.
         * */
        CashButtonConfig.show(activity = this@CashButtonChannelingActivity,
            object : IButtonCallback {
                override fun onSuccess(view: FloatingButtonLayout?) {

                }
            })
    }

    /**
     * 별도의 잠금화면 ON/OFF 설정 필요 시
     * 아래와 같이 커스텀하여 사용가능
     * */
    private fun initCashScreenCustomButton() {
        val screenBtn: Button = findViewById(R.id.CashScreen)

        screenBtn.setOnClickListener {
            if (CashScreenConfig.isOn) {
                CashScreenConfig.stop {}
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
                Intent(this@CashButtonChannelingActivity, TestActivity::class.java),
                "테스트1"
            ),
            CashScreenConfig.ExtraMenuItem(
                menuIconResID = R.drawable.avt_cm_ir_menu_accumulate,
                Intent(this@CashButtonChannelingActivity, TestActivity2::class.java),
                "테스트2"
            )
        )
    }

    private fun setScreenBtnText() {
        val screenBtn: Button = findViewById(R.id.CashScreen)
        screenBtn.text = "잠금화면 : ${if (CashScreenConfig.isOn) "on" else "off"}"
    }

}