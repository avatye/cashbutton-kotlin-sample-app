package com.sudal.cashbutton_2_0_s2s

import android.app.Application
import android.widget.Toast
import com.avatye.cashbutton.product.button.CashButtonSDK
import com.avatye.sdk.cashbutton.launcher.entity.BuzzAdsPID
import com.avatye.sdk.cashbutton.launcher.entity.MarketType
import com.avatye.sdk.cashbutton.launcher.entity.PointType

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        PerfRepository.init(context = this@Application)

        /** 발급받은 서비스 키가 없다면 테스트 키로 적용해주세요.
         *
         *  캐시버튼 테스트 키
         *  appID = "91e4b7f81a6511ea813d0a4916b2361a", appSecret = "aafc702323bf6a214"
         *
         *  캐시버튼 채널링 테스트 키
         *  appID = "2f7dc58210b646e4bae2c6409dcb4a27", appSecret = "1855684419894d5e"
         *
         *  채널링의 경우 CashButtonChannelingActivity에서 phoneNumber를 반드시 설정해주셔야 하며,
         *  서비스 변경 시 설치된 앱 삭제 후 새로 설치해주세요.
         */


        CashButtonSDK(
            application = this,
            appId = "2f7dc58210b646e4bae2c6409dcb4a27",
            appSecret = "1855684419894d5e",
            isAppTestMode = true
        ).apply {
            // debug-mode
            setUseDebug(use = true)

            setBuzzAdsPID(
                BuzzAdsPID(
                    mainPID = "0fbe7a0b-04e8-4a82-8c32-d20bb5155ecd",
                    feedContentPID = "5dea2f82-4f1a-4850-99b1-cbda830b7225",
                    feedShoppingPID = "e4b81c69-0e8d-49a6-ba6b-22d75d4ffa77"
                )
            )

            // point-theme
//            setPointType(pointType = PointType.MILEAGE)

            // market
            setMarketType(marketType = MarketType.GooglePlayStore)
        }.build(listener = object : CashButtonSDK.IResultListener {
            override fun onSuccess() {
                Toast.makeText(
                    this@Application, "CashButtonSDK.Init::onSuccess", Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(reason: String) {
                Toast.makeText(
                    this@Application, "CashButtonSDK.Init::onFailure '$reason'", Toast.LENGTH_SHORT
                ).show()
            }
        })

    }


}