package com.avatye.app.sample.kotlin_cashbutton_sample

import android.app.Application
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.CashButtonPosition
import com.avatye.sdk.cashbutton.core.service.CashNotifyModel

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        /** cash-button-config initializer */
        CashButtonConfig.initializer(this)
    }
}