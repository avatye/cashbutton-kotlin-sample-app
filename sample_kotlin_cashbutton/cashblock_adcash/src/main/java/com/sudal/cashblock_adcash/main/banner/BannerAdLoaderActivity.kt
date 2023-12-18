package com.sudal.cashblock_adcash.main.banner

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashblock.unit.adcash.AdError
import com.avatye.cashblock.unit.adcash.BannerAdSize
import com.avatye.cashblock.unit.adcash.loader.BannerAdLoader
import com.sudal.cashblock_adcash.R
import com.sudal.cashblock_adcash.databinding.ActivityCashBlockBannerAdLoaderBinding

class BannerAdLoaderActivity : AppCompatActivity() {
    companion object {
        val TAG: String = BannerAdLoaderActivity::class.java.simpleName
    }

    private var bannerAdLoader: BannerAdLoader? = null
    private var bottomAdContainer: BannerAdLoader? = null
    private val binding: ActivityCashBlockBannerAdLoaderBinding by lazy {
        ActivityCashBlockBannerAdLoaderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadBanner()
        loadBottomBanner()
    }

    private fun loadBanner() {
        // banner instance
        bannerAdLoader = BannerAdLoader(
            context = this,
            placementId = getString(R.string.adcash_baaner_pid),
            bannerAdSize = BannerAdSize.W300XH250,
            listener = object : BannerAdLoader.BannerListener {
                override fun onLoaded(adView: View, size: BannerAdSize) {
                    // 배너 로드 성공
                    // 사용자 정의 배너뷰 컨테이너(ViewGroup)
                    binding.bannerAdViewContainer.removeAllViews()
                    binding.bannerAdViewContainer.addView(adView)
                    Log.e(TAG, "$TAG -> bannerAdLoader -> listener::onLoaded")
                }

                override fun onFailed(error: AdError) {
                    // 로드 실패
                    // adError.errorCode : 실패코드
                    // adError.errorMessage : 실패사유
                    Log.e(TAG, "$TAG -> bannerAdLoader -> listener::onFailed { errorCode: ${error.errorCode}, errorMessage: ${error.errorMessage} }")
                    Toast.makeText(this@BannerAdLoaderActivity, error.errorMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onClicked() {
                    // 배너 클릭 이벤트
                    Log.e(TAG, "$TAG -> bannerAdLoader -> listener::onClicked")
                }
            }
        )
        // 광고 호출
        bannerAdLoader?.requestAd()
    }


    private fun loadBottomBanner() {
        // banner instance
        bottomAdContainer = BannerAdLoader(
            context = this,
            placementId = getString(R.string.adcash_bottom_linear_common_baaner_pid),
            bannerAdSize = BannerAdSize.DYNAMIC,
            listener = object : BannerAdLoader.BannerListener {
                override fun onLoaded(adView: View, size: BannerAdSize) {
                    // 배너 로드 성공
                    // 사용자 정의 배너뷰 컨테이너(ViewGroup)
                    binding.bottomAdContainer.removeAllViews()
                    binding.bottomAdContainer.addView(adView)
                    Log.e(TAG, "$TAG -> loadBottomBanner -> bannerAdLoader -> listener::onLoaded")
                }

                override fun onFailed(error: AdError) {
                    // 로드 실패
                    // adError.errorCode : 실패코드
                    // adError.errorMessage : 실패사유
                    Log.e(TAG, "$TAG -> loadBottomBanner -> bannerAdLoader -> listener::onFailed { errorCode: ${error.errorCode}, errorMessage: ${error.errorMessage} }")
                    Toast.makeText(this@BannerAdLoaderActivity, error.errorMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onClicked() {
                    // 배너 클릭 이벤트
                    Log.e(TAG, "$TAG -> loadBottomBanner -> bannerAdLoader -> listener::onClicked")
                }
            }
        )
        // 광고 호출
        bottomAdContainer?.requestAd()
    }




    override fun onResume() {
        super.onResume()
        bannerAdLoader?.onResume()
    }

    override fun onPause() {
        super.onPause()
        bannerAdLoader?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        bannerAdLoader?.onDestroy()
    }
}