package com.avatye.aos.cashbutton.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.avatye.aos.cashbutton.R
import com.avatye.aos.cashbutton.SettingActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.IButtonCallback
import com.avatye.sdk.cashbutton.core.entity.cashmore.AvatyeUserData
import com.avatye.sdk.cashbutton.core.widget.FloatingButtonLayout

class CashButtonChannelingActivity : AppCompatActivity() {

    /** 캐시버튼 채널링 테스트 시 본인 번호 설정 */
    private val phoneNumber = "01077915153"

    private var floatingButtonLayout: FloatingButtonLayout? = null

    private lateinit var sharedPref: SharedPreferences

    private val isShowButton
        get() = sharedPref.getBoolean(getString(R.string.saved__key_is_show_button), true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_button_channeling)

        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        initCashButtonChanneling()

        findViewById<Button>(R.id.GoToSetting).setOnClickListener {
            startActivity(Intent(this@CashButtonChannelingActivity, SettingActivity::class.java))
        }

        findViewById<Button>(R.id.channelingButton).setOnClickListener {
            CashButtonConfig.start(this)
        }


    }

    /** 캐시버튼 채널링 기본설정 */
    private fun initCashButtonChanneling() {
        // 1. AvatyeUserData 설정
        val userData = AvatyeUserData(
            userID = "USERID_$phoneNumber", phoneNumber = phoneNumber, nickname = phoneNumber
        )
        CashButtonConfig.userData = userData


        /*
         *  캐시버튼 채널링 실행(start or show)
         *  show : 제공해주는 버튼형태의 진입점을 사용한 방식
         *  start : 별도의 진입 점을 직접 구현하는 방식
         * */
        if (isShowButton) {
            showFloatingButton()
        } else {
            startButton()
        }
    }


    private fun showFloatingButton() {
        CashButtonConfig.show(activity = this@CashButtonChannelingActivity, object : IButtonCallback {
            override fun onSuccess(view: FloatingButtonLayout?) {
                floatingButtonLayout = view
            }
        })
    }


    private fun startButton() {
        CashButtonConfig.start(this)
    }


    override fun onResume() {
        super.onResume()
        if (!isShowButton) {
            /**
             * 채널링 서비스에서 CashButtonConfig.show 사용시 별도로 hide 시키는 기능
             * */
            floatingButtonLayout?.let {
                CashButtonConfig.hide(this)
            }
        } else {
            showFloatingButton()
        }
    }
}