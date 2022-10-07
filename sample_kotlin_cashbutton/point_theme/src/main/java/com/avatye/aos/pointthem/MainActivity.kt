package com.avatye.aos.pointthem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.avatye.sdk.cashbutton.*
import com.avatye.sdk.cashbutton.core.entity.base.PointThemeType
import com.avatye.sdk.cashbutton.core.entity.cashmore.AvatyeUserData
import com.avatye.sdk.cashbutton.ui.CashButtonLayout

class MainActivity : AppCompatActivity() {

    /** 캐시버튼 일 떄 사용 */
    private var cashButtonLayout: CashButtonLayout? = null

    /** 캐시버튼 채널링 테스트 시 본인 번호 설정 */
    private val phoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCashButton()
        initCashButtonChanneling()
        CashButtonConfig.start(this)

        /** 테마 설정 */
        setPointTheme()
    }

    private fun setPointTheme() {
        /** 지정 테마 */
        CashButtonConfig.setPointTheme(PointThemeType.POINT)
        CashButtonConfig.setPointTheme(PointThemeType.MILEAGE)

        /** 사용자 커스텀 테마 (단위 모양 설정 불가) */
        CashButtonConfig.setPointMark(
            R.drawable.avtcb_coin_mark_p_unit,
            "[ThemeName]"
        )

        /** 사용자 커스텀 테마 (단위 모양 설정 가능) */
        CashButtonConfig.setCustomPointTheme(
            R.drawable.avtcb_coin_stroke_unit,
            R.drawable.avtcb_coin_fill_unit,
            R.drawable.avtcb_coin_mark_p_unit,
            "[ThemeName]"
        )
    }

    /** 캐시버튼 기본설정 */
    private fun initCashButton() {
        CashButtonConfig.cashButtonCallback = object : ICashButtonCallback {
            override fun onSuccess(view: CashButtonLayout?) {
                cashButtonLayout = view
            }

            override fun onDashBoardStateChange(state: CashButtonLayout.State) {
                super.onDashBoardStateChange(state)
                Log.i("MainActivity", "onDashBoardStateChange : $state")

            }
        }
    }

    /** 캐시버튼 채널링 기본설정 */
    private fun initCashButtonChanneling() {
        val userData = AvatyeUserData(
            userID = "USERID_$phoneNumber", phoneNumber = phoneNumber, nickname = phoneNumber
        )

        CashButtonConfig.userData = userData
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