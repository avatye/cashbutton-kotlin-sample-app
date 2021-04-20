package com.avatye.app.sample.kotlin_cashbutton_sample

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.ICashButtonCallback
import com.avatye.sdk.cashbutton.ui.CashButtonLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

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

        // region { 캐시버튼 설정 }
        sw_cashbutton_state.setOnCheckedChangeListener(this)
        // endregion

        // region { 노티바 설정 }
        sw_notibar_state.setOnCheckedChangeListener(this)
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
        })

        component_header_view.setBack { onBackPressed() }

        CashButtonConfig.initInviteInfo("캐시 버튼에서 친구 초대를 할 때 사용하는 메시지입니다.")

        sw_cashbutton_state.isChecked = CashButtonConfig.getCashButtonState()
        sw_notibar_state.isChecked = CashButtonConfig.getAllowNotificationBar()
    }


    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.sw_cashbutton_state -> {
                if (isChecked) {
                    CashButtonConfig.setCashButtonSnoozeOff()
                } else {
                    CashButtonConfig.setCashButtonSnoozeOn(1)
                }
            }

            R.id.sw_notibar_state -> {
                CashButtonConfig.setAllowNotificationBar(activity = this, value = isChecked)
            }
        }
    }

}