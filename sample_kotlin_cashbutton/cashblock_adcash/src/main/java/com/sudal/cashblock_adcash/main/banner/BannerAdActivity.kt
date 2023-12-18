package com.sudal.cashblock_adcash.main.banner

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashblock.unit.adcash.AdError
import com.avatye.cashblock.unit.adcash.BannerAdSize
import com.avatye.cashblock.unit.adcash.view.BannerAdView
import com.sudal.cashblock_adcash.R
import com.sudal.cashblock_adcash.databinding.ActivityCashBlockBannerAdBinding

class BannerAdActivity : AppCompatActivity() {
    companion object {
        val TAG: String = BannerAdActivity::class.java.simpleName
    }

    private val binding: ActivityCashBlockBannerAdBinding by lazy {
        ActivityCashBlockBannerAdBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // banner size
        // xml에서 정의 했다면 설정 하지 않는다.
        binding.bannerAdView.setBannerAdSize(BannerAdSize.DYNAMIC)
        // placementId
        // xml에서 정의 했다면 설정 하지 않는다.
        binding.bannerAdView.setPlacementId(placementId = getString(R.string.adcash_baaner_pid))
        binding.bannerAdView.listener = object : BannerAdView.Listener {
            override fun onLoaded() {
                // 로드 성공
                Log.e(TAG, "$TAG -> bannerAdView -> listener::onLoaded")
            }

            override fun onFailed(error: AdError) {
                // 로드 실패
                // adError.errorCode : 실패코드
                // adError.errorMessage : 실패사유
                Log.e(TAG, "$TAG -> bannerAdView -> listener::onFailed { errorCode: ${error.errorCode}, errorMessage: ${error.errorMessage} }")
                Toast.makeText(this@BannerAdActivity, error.errorMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onClicked() {
                // 배너 클릭 이벤트
                Log.e(TAG, "$TAG -> bannerAdView -> listener::onClicked")
            }
        }
        // 광고 호출
        binding.bannerAdView.requestAd()
    }


    override fun onResume() {
        super.onResume()
        binding.bannerAdView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.bannerAdView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.bannerAdView.onDestroy()
    }
}