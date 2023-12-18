package com.sudal.cashblock_adcash.main.interstitial

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashblock.unit.adcash.AdError
import com.avatye.cashblock.unit.adcash.InterstitialAdType
import com.avatye.cashblock.unit.adcash.loader.InterstitialAdLoader
import com.sudal.cashblock_adcash.R

class InterstitialAdActivity: AppCompatActivity() {

    companion object {
        val TAG: String = InterstitialAdActivity::class.java.simpleName
    }

    private var interstitialAdLoader: InterstitialAdLoader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        interstitialAdLoader = InterstitialAdLoader(
            ownerActivity = this,
            placementId = getString(R.string.adcash_open_interstitial_baaner_pid),
            listener = object : InterstitialAdLoader.InterstitialListener {
                override fun onLoaded(executor: InterstitialAdLoader.InterstitialExecutor, adType: InterstitialAdType) {
                    // interstitialExecutor의 show() 함수를 통해 광고를 노출 합니다.
                    executor.show()
                    Log.e(TAG, "$TAG -> interstitialAdLoader -> listener::onLoaded")
                }

                override fun onOpened() {
                    // 광고 노출 성공
                    Log.e(TAG, "$TAG -> interstitialAdLoader -> listener::onOpened")
                }

                override fun onClosed(completed: Boolean) {
                    // 광고 종료
                    // completed: 리워드 광고 시청 완료 또는 일반 지면 정상 종료 여부
                    Log.e(TAG, "$TAG -> interstitialAdLoader -> listener::onClosed { completed:$completed }")
                }

                override fun onFailed(error: AdError) {
                    // 광고 오류
                    Log.e(TAG, "$TAG -> interstitialAdLoader -> listener::onFailed { errorCode: ${error.errorCode}, errorMessage: ${error.errorMessage} }")
                    Toast.makeText(this@InterstitialAdActivity, error.errorMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onClicked() {
                    // 광고 클릭
                    Log.e(TAG, "$TAG -> interstitialAdLoader -> listener::onClicked")
                }
            }
        )
        interstitialAdLoader?.requestAd()
    }

    override fun onResume() {
        super.onResume()
        interstitialAdLoader?.onResume()
    }

    override fun onPause() {
        super.onPause()
        interstitialAdLoader?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        interstitialAdLoader?.onDestroy()
    }
}