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
        sw_notibar_state.setOnCheckedChangeListener(this)
        sw_cashbutton_state.setOnCheckedChangeListener(this)
        ly_inquire_container.setOnClickListener(this)
    }


    private fun initCommon() {
        component_header_view.setBack { onBackPressed() }

        /** initialize cashbutton view */
        CashButtonLayout.init(this@MainActivity, object : ICashButtonCallback {
            override fun onSuccess(view: CashButtonLayout?) {
                cashButtonLayout = view
            }
        })

        sw_cashbutton_state.isChecked = CashButtonConfig.getCashButtonState()
        sw_notibar_state.isChecked = CashButtonConfig.getAllowNotificationBar()

    }


    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.sw_cashbutton_state -> {
                cashbuttonAndNotibarRunnable = Runnable {
                    if(isChecked){
                        CashButtonConfig.setCashButtonSnoozeOff()
                    } else {
                        CashButtonConfig.setCashButtonSnoozeOn(1)
                    }

                }
                var cashnotiR = CashbuttonAndNotibarWorkRunnable()
                Thread(cashnotiR).start()
            }
            R.id.sw_notibar_state -> {
                notibarRunnable = Runnable {
                    CashButtonConfig.setAllowNotificationBar(activity = this, value = isChecked)
                }
                var notiR = NotibarWorkRunnable()
                Thread(notiR).start()
            }
        }
    }

    
    override fun onClick(v: View?) {
        if(v?.id === R.id.ly_inquire_container){
            CashButtonConfig.actionSuggestion(this)
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