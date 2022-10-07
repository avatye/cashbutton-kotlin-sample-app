package com.avatye.aos.cashscreen

import android.app.Application
import com.avatye.sdk.cashbutton.CashButtonConfig

class ScreenApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        CashButtonConfig.initializer(this)

    }
}