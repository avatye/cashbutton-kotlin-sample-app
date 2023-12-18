package com.sudal.cashblock_adcash.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sudal.cashblock_adcash.databinding.ActivityCashBlockAdcashBinding
import com.sudal.cashblock_adcash.main.banner.BannerAdActivity
import com.sudal.cashblock_adcash.main.banner.BannerAdLoaderActivity
import com.sudal.cashblock_adcash.main.interstitial.InterstitialAdActivity
import com.sudal.cashblock_adcash.main.interstitial.RewardInterstitialAdActivity

class ADCashActivity : AppCompatActivity() {

    private val binding: ActivityCashBlockAdcashBinding by lazy {
        ActivityCashBlockAdcashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 배너광고
        binding.bannerAd.setOnClickListener {
            startActivity(Intent(this@ADCashActivity, BannerAdActivity::class.java))
        }

        // 배너광고(로더형식) : 배너광고+하단광고
        binding.bannerLoaderAd.setOnClickListener {
            startActivity(Intent(this@ADCashActivity, BannerAdLoaderActivity::class.java))
        }

        // 전면광고
        binding.interstitalAd.setOnClickListener {
            startActivity(Intent(this@ADCashActivity, InterstitialAdActivity::class.java))
        }

        // 리워드 전면광고
        binding.rewardInterstitalAd.setOnClickListener {
            startActivity(Intent(this@ADCashActivity, RewardInterstitialAdActivity::class.java))
        }
    }
}