package com.sudal.cashbutton_webview.main.java;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.avatye.cashbutton.product.button.LaunchButtonBuilder;
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener;
import com.avatye.sdk.cashbutton.launcher.listener.ILaunchButtonListener;
import com.avatye.sdk.cashbutton.ui.CashButtonLayout;
import com.sudal.cashbutton_webview.databinding.ActivityCashButtonBinding;

public class CashButtonJavaActivity extends AppCompatActivity {

    private ActivityCashButtonBinding binding;
    private LaunchButtonBuilder buttonBuilder;
    private CashButtonLayout cashButtonLayout;

    @Override
    public void onBackPressed() {
        if (cashButtonLayout != null) {
            cashButtonLayout.onBackPressed(new ICashButtonBackPressedListener() {
                @Override
                public void onBackPressed(boolean isSuccess) {
                    if (isSuccess) {
                        finish();
                    }
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCashButtonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.headerView.setText("Java 프로젝트");

        WebSettings webSettings = binding.webView.getSettings();
        // JavaScript 활성화
        webSettings.setJavaScriptEnabled(true);

        // JS 함수 연결
        JavaScriptInterface jsInterface = new JavaScriptInterface();

        // 태그 를 이용해서 JS에서 안드로이드 함수 호출 가능
        // JS 코드 [window.태그.callAndroidFunction();]
        binding.webView.addJavascriptInterface(jsInterface, "Android");

        // URL 대신 asset 파일에서 HTML을 불러오도록 설정되어있음.
        // 실사용 시 사용할 Url로 변경바람.
        binding.webView.loadUrl("file:///android_asset/sample.html");


        LaunchButtonBuilder.Builder launchButtonBuilder = new LaunchButtonBuilder.Builder(this);
        launchButtonBuilder.build(new LaunchButtonBuilder.IBuilderListener() {
            @Override
            public void onSuccess(@NonNull LaunchButtonBuilder launchButtonBuilder) {
                // 성공
                // builder.launchButton or builder.launchCustomView
                buttonBuilder = launchButtonBuilder;
                launchButton();
            }

            @Override
            public void onFailure(@NonNull String s) {
                // 실패
                // reason: 실패 사유
            }
        });
    }


    public void launchButton() {
        buttonBuilder.launchButton(this, new ILaunchButtonListener() {
            @Override
            public void onSuccess(CashButtonLayout view) {
                cashButtonLayout = view;
            }

            @Override
            public void onFailure(String reason) {
            }

            @Override
            public void onDashBoardStateChange(CashButtonLayout.State state) {
            }
        });
    }


    public class JavaScriptInterface {
        @JavascriptInterface
        public void callAndroidFunction() {
            /**
             * 둘중에 하나만 사용해주세요.
             * launchButton() or launchCustomView()
             * launchButton(): 기본형
             * launchCustomView(): 커스텀형
             */
            launchButton();
            // launchCustomView();
        }
    }
}
