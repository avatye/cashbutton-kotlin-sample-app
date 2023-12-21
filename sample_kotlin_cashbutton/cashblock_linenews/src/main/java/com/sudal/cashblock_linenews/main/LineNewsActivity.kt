package com.sudal.cashblock_linenews.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashblock.unit.linenews.view.LineNewsView
import com.sudal.cashblock_linenews.R
import com.sudal.cashblock_linenews.databinding.ActivityCashBlockLinenewsBinding

class LineNewsActivity : AppCompatActivity() {

    private val binding: ActivityCashBlockLinenewsBinding by lazy {
        ActivityCashBlockLinenewsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.lineNewsView) {
            setUseBanner(use = true)
            setNewsIcon(R.drawable.avatar_image)
            setNewsTextColorResource(
                textColor = R.color.black,
                clickTextColor = R.color.acb_resource_color_greyish_brown
            )
            setPlacementId(placementId = getString(R.string.linenews_placement_id))
            setCheckStrategy(checkStrategyType = LineNewsView.CheckStrategy.BANNER_ONLY)
            setLineNewsResult(listener = object : LineNewsView.ILineNewsResult {
                override fun onSuccess() {
                    Log.e("asd", "LineNewsActivity -> setLineNewsResult::onSuccess")
                    binding.lineNewsViewLog.text = "onAdLoaded"
                }

                override fun onAdFailed(errorCode: Int, errorMessage: String) {
                    Log.e("asd", "LineNewsActivity -> setLineNewsResult::onAdFailed { errorCode:$errorCode, errorMessage:$errorMessage }")
                    binding.lineNewsViewLog.text = "onAdFailed{ errorCode:$errorCode, errorMessage:$errorMessage }"
                }

                override fun onNewsClicked() {
                    Log.e("asd", "LineNewsActivity -> setLineNewsResult::onNewsClicked")
                    binding.lineNewsViewLog.text = "onNewsClicked"
                }

                override fun onAdClicked() {
                    Log.e("asd", "LineNewsActivity -> setLineNewsResult::onAdClicked")
                    binding.lineNewsViewLog.text = "onAdClicked"
                }
            })
        }
        binding.buttonLoad.setOnClickListener {
            binding.lineNewsView.request()
        }
    }

    override fun onResume() {
        binding.lineNewsView.onResume()
        super.onResume()
    }

    override fun onPause() {
        binding.lineNewsView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.lineNewsView.onDestroy()
        super.onDestroy()
    }
}