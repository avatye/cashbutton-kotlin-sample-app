package com.avatye.aos.cashbutton

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class SettingActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    private val isShowButton
        get() = sharedPref.getBoolean(getString(R.string.saved__key_is_show_button), true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        sharedPref =
            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val switchButton = findViewById<SwitchCompat>(R.id.SwitchButton)
        switchButton.isChecked = isShowButton

        switchButton?.setOnCheckedChangeListener { _, checked ->
            switchButton.isChecked = checked
            with(sharedPref.edit()) {
                putBoolean(getString(R.string.saved__key_is_show_button), checked)
                apply()
            }
        }
    }
}