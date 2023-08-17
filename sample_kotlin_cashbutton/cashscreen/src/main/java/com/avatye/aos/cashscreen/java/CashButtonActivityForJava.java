package com.avatye.aos.cashscreen.java;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.avatye.aos.cashscreen.R;
import com.avatye.aos.cashscreen.TestActivity;
import com.avatye.aos.cashscreen.TestActivity2;
import com.avatye.sdk.cashbutton.CashButtonConfig;
import com.avatye.sdk.cashbutton.IButtonCallback;
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener;
import com.avatye.sdk.cashbutton.ICashButtonCallback;
import com.avatye.sdk.cashbutton.core.cashscreen.CashScreenConfig;
import com.avatye.sdk.cashbutton.core.cashscreen.ICashScreenCallback;
import com.avatye.sdk.cashbutton.core.external.CashButtonEvent;
import com.avatye.sdk.cashbutton.core.external.ICashButtonBalanceListener;
import com.avatye.sdk.cashbutton.core.widget.FloatingButtonLayout;
import com.avatye.sdk.cashbutton.ui.CashButtonLayout;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class CashButtonActivityForJava extends AppCompatActivity {

    /**
     * 캐시버튼 일 떄 사용
     */
    private CashButtonLayout cashButtonLayout = null;

    /**
     * 캐시버튼 이벤트 받을 때 사용
     */
    private CashButtonEvent.Builder balanceEventBuilder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_button_for_java);

        initCashButton();

        initMenuItem();
        initCashScreenCustomButton();

        CashButtonConfig.show(this, new IButtonCallback() {
            @Override
            public void onSuccess(@Nullable FloatingButtonLayout floatingButtonLayout) {

            }
        });
    }

    /**
     * 캐시버튼 기본설정
     */
    private void initCashButton() {

        /** 커스텀 캐시버튼 사용 시 설정 */
        if (CashButtonConfig.getUseCustomCashButton()) {
            initCustomCashButton();
        } else {
            /** initialize cashbutton view */
            CashButtonConfig.INSTANCE.setCashButtonCallback(new ICashButtonCallback() {
                @Override
                public void onSuccess(@Nullable CashButtonLayout view) {
                    cashButtonLayout = view;
                }

                @Override
                public void onDashBoardStateChange(@NonNull CashButtonLayout.State state) {
                    Log.e("MainActivity", "MainAcitivity -> onDashBoardStateChange : $state");
                }

            });
        }
    }

    /**
     * 커스텀 캐시버튼 사용 시 설정
     */
    private void initCustomCashButton() {
        Button customButtonView = findViewById(R.id.CustomCashButton);
        customButtonView.setEnabled(true);

        /** 코인, 캐시, 티켓 이벤트를 이용해 자유롭게 커스텀 가능 */
        balanceEventBuilder = new CashButtonEvent.Builder(this)
                .addListener(new ICashButtonBalanceListener() {
                    @Override
                    public void onBalanceUpdate(@NonNull String coin, int cash, int ticket) {
                        customButtonView.setText(coin);
                    }
                }).build();

        customButtonView.setOnClickListener(view -> CashButtonConfig.start(CashButtonActivityForJava.this));
    }

    /**
     * 별도의 잠금화면 ON/OFF 설정 필요 시
     * 아래와 같이 커스텀하여 사용가능
     */
    private void initCashScreenCustomButton() {
        Button screenBtn = findViewById(R.id.CashScreen);

        screenBtn.setOnClickListener(view -> {
            if (CashScreenConfig.INSTANCE.isOn()) {
                CashScreenConfig.INSTANCE.stop(() -> null);
            } else {
                CashScreenConfig.INSTANCE.start(CashButtonActivityForJava.this);
            }
        });

        setScreenBtnText();

        CashScreenConfig.setOnCashScreenCallback(new ICashScreenCallback() {
            @Override
            public void onStart() {
                setScreenBtnText();
            }

            @Override
            public void onStop() {
                setScreenBtnText();
            }
        });
    }

    /**
     * 잠금화면 메뉴 추가
     * 잠금화면에서 앱(파트너사의 어플리케이션)의 다른 회면에 접근 가능하도록 메뉴를 추가하실 수 있습니다.(최대 2개)
     */
    private void initMenuItem() {
        CashScreenConfig.INSTANCE.setLandingIntent(
                new CashScreenConfig.ExtraMenuItem(
                        R.drawable.avt_cm_ir_menu_exchange,
                        new Intent(CashButtonActivityForJava.this, TestActivity.class),
                        "테스트1"
                ),
                new CashScreenConfig.ExtraMenuItem(
                        R.drawable.avt_cm_ir_menu_accumulate,
                        new Intent(CashButtonActivityForJava.this, TestActivity2.class),
                        "테스트2"
                )
        );
    }

    @SuppressLint("SetTextI18n")
    private void setScreenBtnText() {
        Button screenBtn = findViewById(R.id.CashScreen);

        if (CashScreenConfig.INSTANCE.isOn())
            screenBtn.setText("잠금화면 : on");
        else
            screenBtn.setText("잠금화면 : off");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (cashButtonLayout == null) {
            return;
        }

        cashButtonLayout.onBackPressed(new ICashButtonBackPressedListener() {
            @Override
            public void onBackPressed(boolean isSuccess) {
                if (isSuccess) {
                    finish();
                }
            }
        });
    }

    /**
     * 등록한 이벤트 해제
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (balanceEventBuilder == null) {
            return;
        }

        balanceEventBuilder.destroy();
    }
}