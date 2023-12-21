package com.sudal.cashblock_linenews

import android.app.Application
import com.avatye.cashblock.unit.linenews.LineNewsSDK
import com.sudal.cashblock_adcash.PerfRepository

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        PerfRepository.init(context = this@Application)

        /** 발급받은 서비스 키가 없다면 테스트 키로 적용해주세요.
         *
         *  한줄뉴스 테스트 키
         *  appID = "fe313ec5eb42455c8972f9fdbd6ce7cf", appSecret = "3027b9ba2df14d05"
         */

        val lineNewsBuilder = LineNewsSDK.Builder(
            context = this@Application,
            appId = "fe313ec5eb42455c8972f9fdbd6ce7cf",
            appSecret = "3027b9ba2df14d05"
        )
        lineNewsBuilder.build()
    }
}