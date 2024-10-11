package com.sudal.cashblock_adcash

import android.app.Application
import com.avatye.cashblock.unit.adcash.ADCashSDK

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        PerfRepository.init(context = this@Application)

        /** 발급받은 서비스 키가 없다면 테스트 키로 적용해주세요.
         *
         *  애드캐시 테스트 키
         *  appID = "6da4ad56527f409d9e3f97700437abc0", appSecret = "09ff5525d0e44ed8"
         *
         */

        ADCashSDK.initialize(
            context = this,
            appId = "6da4ad56527f409d9e3f97700437abc0",
            appSecret = "09ff5525d0e44ed8",
            storeUrl = "https://www.avatye.com"
        )
    }
}