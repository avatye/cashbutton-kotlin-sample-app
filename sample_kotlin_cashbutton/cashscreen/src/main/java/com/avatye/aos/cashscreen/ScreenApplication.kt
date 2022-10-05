package com.avatye.aos.cashscreen

import android.app.Application
import com.avatye.sdk.cashbutton.CashButtonChannelingConfig

class ScreenApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        CashButtonChannelingConfig.initializer(
            this,
            "69f43822b64d46d6bc01ff6d282f754d",
            "077b11dc468f4bd3"
        )

    }
}