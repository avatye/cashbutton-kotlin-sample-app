package com.avatye.aos.cashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.core.cashscreen.CashScreenConfig

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addMenuItem()

        val screenBtn: Button = findViewById(R.id.screenbtn)
        setScreenBtnText()

        screenBtn.setOnClickListener {
            if (CashScreenConfig.isOn) {
                CashScreenConfig.stop()
            } else {
                CashScreenConfig.start(this)
            }

            setScreenBtnText()
        }

    }

    /**
     * 잠금화면 메뉴 추가
     * 잠금화면에서 앱(파트너사의 어플리케이션)의 다른 회면에 접근 가능하도록 메뉴를 추가하실 수 있습니다.(최대 2개)
     * */
    private fun addMenuItem() {
        CashScreenConfig.setLandingIntent(
            CashScreenConfig.ExtraMenuItem(
                menuIconResID = R.drawable.avt_cm_ir_menu_exchange,
                Intent(this@MainActivity, TestActivity::class.java),
                "테스트1"
            ),
            CashScreenConfig.ExtraMenuItem(
                menuIconResID = R.drawable.avt_cm_ir_menu_accumulate,
                Intent(this@MainActivity, TestActivity2::class.java),
                "테스트2"
            )
        )
    }

    private fun setScreenBtnText() {
        val screenBtn: Button = findViewById(R.id.screenbtn)
        screenBtn.text = "잠금화면 : ${if (CashScreenConfig.isOn) "on" else "off"}"
    }

}