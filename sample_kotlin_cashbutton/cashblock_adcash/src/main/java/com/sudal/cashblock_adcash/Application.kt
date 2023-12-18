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
         *  appID = "98d4d4c35d594451b21f54718e2bc986", appSecret = "c395dbe200ad4493ade96fb92c988fcf1c8df2d3687d49a9ab6f31f7c05e2bf4"
         *
         */

        ADCashSDK.initialize(
            context = this,
            appId = "98d4d4c35d594451b21f54718e2bc986",
            appSecret = "c395dbe200ad4493ade96fb92c988fcf1c8df2d3687d49a9ab6f31f7c05e2bf4",
            storeUrl = "https://www.avatye.com"
        )
    }
}