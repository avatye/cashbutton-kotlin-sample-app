package com.avatye.aos.cashbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.IButtonCallback
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.ICashButtonCallback
import com.avatye.sdk.cashbutton.core.entity.cashmore.AvatyeUserData
import com.avatye.sdk.cashbutton.core.widget.FloatingButtonLayout
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
//         CashButtonConfig.show(activity = this@MainActivity, object: IButtonCallback {
//             override fun onSuccess(view: FloatingButtonLayout?) {
//
//            }
//         })
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