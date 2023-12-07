package com.sudal.cashbutton_webview.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashbutton.product.button.LaunchButtonBuilder
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchButtonListener
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchCustomViewListener
import com.avatye.sdk.cashbutton.ui.CashButtonLayout
import com.sudal.cashbutton_webview.databinding.ActivityCashButtonBinding

class CashButtonKotlinActivity : AppCompatActivity() {

    private var cashButtonLayout: CashButtonLayout? = null

    private lateinit var buttonBuilder: LaunchButtonBuilder

    private val binding: ActivityCashButtonBinding by lazy {
        ActivityCashButtonBinding.inflate(layoutInflater)
    }


    override fun onBackPressed() {
        cashButtonLayout?.onBackPressed(object : ICashButtonBackPressedListener {
            override fun onBackPressed(isSuccess: Boolean) {
                if (isSuccess) {
                    finish()
                }
            }
        }) ?: run {
            super.onBackPressed()
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.headerView.text = "Kotlin 프로젝트"

        val webView: WebView = binding.webView

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        val jsInterface = JavaScriptInterface()
        webView.addJavascriptInterface(jsInterface, "Android")

        binding.webView.loadUrl("file:///android_asset/sample.html")

        val launchButtonBuilder = LaunchButtonBuilder.Builder(context = this@CashButtonKotlinActivity)
        launchButtonBuilder.build(listener = object : LaunchButtonBuilder.IBuilderListener {
            override fun onSuccess(builder: LaunchButtonBuilder) {
                Log.i("LaunchButtonBuilder", "IBuilderListener::onSuccess")
                this@CashButtonKotlinActivity.buttonBuilder = builder
                launchButton()
            }

            override fun onFailure(reason: String) {
                Log.i("LaunchButtonBuilder", "IBuilderListener::onFailure '$reason'")
            }
        })
    }


    private fun launchButton() {
        buttonBuilder.launchButton(
            ownerActivity = this@CashButtonKotlinActivity,
            listener = object : ILaunchButtonListener {
                override fun onSuccess(view: CashButtonLayout?) {
                    Log.i("LaunchButtonBuilder", "IBuilderListener::onSuccess")
                    cashButtonLayout = view
                }

                override fun onFailure(reason: String) {
                    Log.i("LaunchButtonBuilder", "ILaunchButtonListener::onFailure '$reason'")
                }

                override fun onDashBoardStateChange(state: CashButtonLayout.State) {
                    super.onDashBoardStateChange(state)
                    Log.i(
                        "LaunchButtonBuilder",
                        "ILaunchButtonListener::onDashBoardStateChange '$state'"
                    )
                }
            }
        )
    }


    private fun launchCustomView() {
        buttonBuilder.launchCustomView(
            ownerActivity = this@CashButtonKotlinActivity,
            customView = null,
            listener = object : ILaunchCustomViewListener {
                override fun onSuccess(view: CashButtonLayout?) {
                    Log.e("LaunchButtonBuilder", "launchCustomView -> onSuccess::view { $view }")
                    cashButtonLayout = view
                    cashButtonLayout?.showDashBoard()
                }

                override fun onCoinUpdate(coinValue: String) {
                    Log.e("LaunchButtonBuilder", "launchCustomView -> onCoinUpdate")
                }

                override fun onFailure(reason: String) {
                    Log.e("LaunchButtonBuilder", "launchCustomView -> onFailure::reason { $reason }")
                }
            }
        )
        cashButtonLayout?.showDashBoard()
    }


    inner class JavaScriptInterface {
        @JavascriptInterface
        fun callAndroidFunction() {
            /**
             * 두개의 메서드 중 하나의 메서드만 사용 해 주세요.
             * 플로팅 버튼 or 커스텀 버튼
             * launchButton() or launchCustomView()
             */
//            launchButton()
//            launchCustomView()
        }
    }
}