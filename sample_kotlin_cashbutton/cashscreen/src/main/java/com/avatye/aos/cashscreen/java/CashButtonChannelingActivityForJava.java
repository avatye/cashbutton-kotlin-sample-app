package com.avatye.aos.cashscreen.java;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.avatye.aos.cashscreen.R;
import com.avatye.aos.cashscreen.TestActivity;
import com.avatye.aos.cashscreen.TestActivity2;
import com.avatye.sdk.cashbutton.CashButtonConfig;
import com.avatye.sdk.cashbutton.IButtonCallback;
import com.avatye.sdk.cashbutton.core.cashscreen.CashScreenConfig;
import com.avatye.sdk.cashbutton.core.cashscreen.ICashScreenCallback;
import com.avatye.sdk.cashbutton.core.entity.cashmore.AvatyeUserData;
import com.avatye.sdk.cashbutton.core.widget.FloatingButtonLayout;

public class CashButtonChannelingActivityForJava extends AppCompatActivity {

    /**
     * 캐시버튼 채널링 테스트 시 본인 번호 설정
     */
    private String phoneNumber = "01031005024";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_button_channeling_for_java);

        initCashButtonChanneling();

        initMenuItem();
        initCashScreenCustomButton();

        Button customCashButton = findViewById(R.id.CustomCashButton);

        customCashButton.setOnClickListener(view -> CashButtonConfig.start(CashButtonChannelingActivityForJava.this));
    }

    /**
     * 캐시버튼 채널링 기본설정
     */
    private void initCashButtonChanneling() {
        AvatyeUserData userData = new AvatyeUserData(
                "USERID_" + phoneNumber, phoneNumber, phoneNumber
        );

        CashButtonConfig.INSTANCE.setUserData(userData);

        /**
         * 채널링 서비스 이용 시 별도의 진입 점을 이용하지 않고
         * 기본 제공되는 버튼 진입점을 사용시 start를 호출하지 않고 show를 호출한다.
         * */
        CashButtonConfig.show(CashButtonChannelingActivityForJava.this, new IButtonCallback() {
            @Override
            public void onSuccess(@Nullable FloatingButtonLayout floatingButtonLayout) {

            }
        });
    }

    /**
     * 별도의 잠금화면 ON/OFF 설정 필요 시
     * 아래와 같이 커스텀하여 사용가능
     */
    private void initCashScreenCustomButton() {
        Button screenBtn = findViewById(R.id.CashScreen);

        screenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CashScreenConfig.INSTANCE.isOn()) {
                    CashScreenConfig.INSTANCE.stop();
                } else {
                    CashScreenConfig.INSTANCE.start(CashButtonChannelingActivityForJava.this);
                }
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
                        new Intent(CashButtonChannelingActivityForJava.this, TestActivity.class),
                        "테스트1"
                ),
                new CashScreenConfig.ExtraMenuItem(
                        R.drawable.avt_cm_ir_menu_accumulate,
                        new Intent(CashButtonChannelingActivityForJava.this, TestActivity2.class),
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

}