package com.avatye.aos.pointthem

import android.app.Application
import com.avatye.sdk.cashbutton.CashButtonConfig

class PointThemApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        /** 갖고있는 서비스 키가 없다면 테스트 키로 확인 가능합니다.
         *
         *  캐시버튼 테스트 키
         *  appID = "91e4b7f81a6511ea813d0a4916b2361a", appSecret = "aafc702323bf6a214"
         *
         *  캐시버튼 채널링 테스트 키
         *  appID = "69f43822b64d46d6bc01ff6d282f754d", appSecret = "077b11dc468f4bd3"
         *
         *  서비스 변경 시 설치된 앱 삭제 후 새로 설치해주세요.
         * */
         CashButtonConfig.initializer(application = this, appID = "", appSecret = "")

        CashButtonConfig.setDebugMode(true)
    }
}