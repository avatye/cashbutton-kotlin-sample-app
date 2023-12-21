package com.sudal.cashblock_box

import android.app.Application
import com.avatye.cashblock.unit.cashbox.CashBoxSDK
import com.sudal.cashblock_adcash.PerfRepository

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        PerfRepository.init(context = this@Application)

        /** 발급받은 서비스 키가 없다면 테스트 키로 적용해주세요.
         *
         *  캐시박스 테스트 키
         *  appID = "98d4d4c35d594451b21f54718e2bc986", appSecret = "c395dbe200ad4493ade96fb92c988fcf1c8df2d3687d49a9ab6f31f7c05e2bf4"
         *
         *  넥슨 테스트 키
         *  appID = "e5a6e5f3edc24b37b5de178ff2ce2e50", appSecret = "7a88e041a63842ad818f1e064a3bf30268f694ed0df846c2a91cc009c1f02d73"
         */
        val builder = CashBoxSDK.Builder(
            context = this@Application,
            appId = "e5a6e5f3edc24b37b5de178ff2ce2e50",
            appSecret = "7a88e041a63842ad818f1e064a3bf30268f694ed0df846c2a91cc009c1f02d73"
        )
        builder.build()
    }
}