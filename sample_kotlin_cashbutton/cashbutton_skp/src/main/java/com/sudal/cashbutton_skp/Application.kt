package com.sudal.cashbutton_skp

import android.app.Application
import android.widget.Toast
import com.avatye.cashbutton.product.button.CashButtonSDK
import com.avatye.sdk.cashbutton.core.entity.SKProductType
import com.avatye.sdk.cashbutton.launcher.entity.MarketType

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        PerfRepository.init(context = this@Application)

        /** 발급받은 서비스 키가 없다면 테스트 키로 적용해주세요.
         *
         *  OK캐시백 키
         *  appID = "02748f8a10e0455289a5cc42cbd91520", appSecret = "52baf3df979d494c"
         *  OK캐시백 테스트 키
         *  appID = "db666d4044024d00b0c597c9391ed8c6", appSecret = "9421e9dfe2e94645"
         *
         *
         *  시럽 키
         *  appID = "361cd951230c44e0bb3254c5e86fd7e5", appSecret = "28b17c958b2b4f9e"
         *  시럽 테스트 키
         *  appID = "dba415532e304fb9b38c571a5ea05f25", appSecret = "85d5a0b92ba04c2c"
         *
         */


        CashButtonSDK(
            application = this, appId = "361cd951230c44e0bb3254c5e86fd7e5", appSecret = "28b17c958b2b4f9e", skProductType = SKProductType.SYRUP
        ).apply {
            // debug-mode
            setUseDebug(use = true)

            // market
            setMarketType(marketType = MarketType.GooglePlayStore)
        }.build(listener = object : CashButtonSDK.IResultListener {
            override fun onCompleted(resultCode: Int) {
                Toast.makeText(
                    this@Application, "CashButtonSDK.Init::onCompleted '$resultCode'", Toast.LENGTH_SHORT
                ).show()
            }
        })

    }


}