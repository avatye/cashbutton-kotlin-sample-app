package com.avatye.app.sample.kotlin_cashbutton_sample

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.ICashButtonCallback
import com.avatye.sdk.cashbutton.ui.CashButtonLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private var cashButtonLayout: CashButtonLayout? = null

    lateinit var notibarRunnable: Runnable;
    lateinit var cashbuttonAndNotibarRunnable: Runnable;


    override fun onBackPressed() {
        super.onBackPressed()
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
        setContentView(R.layout.activity_main)

        // region {init CashButton}
        initCommon()
        // endregion
        sw_notibar_state.setOnCheckedChangeListener(this)
        sw_cashbutton_notibar_state.setOnCheckedChangeListener(this)
        ly_inquire_container.setOnClickListener(this)


    }


    private fun initCommon() {
        component_header_view.setBack { onBackPressed() }

        /** initialize cashbutton view */
        CashButtonLayout.init(this@MainActivity, object : ICashButtonCallback {
            override fun onSuccess(view: CashButtonLayout) {
                cashButtonLayout = view
            }
        })
        CashButtonLayout.initInviteInfo("캐시 버튼에서 친구 초대를 사용할 때 사용하는 메시지입니다.")
        sw_cashbutton_notibar_state.isChecked = CashButtonConfig.getCashButtonState()

    }


    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.sw_notibar_state -> {
                notibarRunnable = Runnable {
                    CashButtonConfig.setCashButtonNotify(isChecked)
                }
                var notiR = NotibarWorkRunnable()
                Thread(notiR).start()

            }
            R.id.sw_cashbutton_notibar_state -> {
                cashbuttonAndNotibarRunnable = Runnable {
                    CashButtonConfig.setCashButtonState(isChecked)
                }
                var cashnotiR = CashbuttonAndNotibarWorkRunnable()
                Thread(cashnotiR).start()
            }

        }

    }

    
    override fun onClick(v: View?) {
        if(v?.id === R.id.ly_inquire_container){
            // region { 캐시버튼 문의 }
            CashButtonConfig.actionSuggestion(this)
            // endregion
        }
    }



    inner class NotibarWorkRunnable : Runnable {
        override fun run() {
            runOnUiThread(notibarRunnable)
        }
    }


    inner class CashbuttonAndNotibarWorkRunnable : Runnable {
        override fun run() {
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            runOnUiThread(cashbuttonAndNotibarRunnable)
        }
    }

}