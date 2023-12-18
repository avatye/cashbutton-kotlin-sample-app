package com.sudal.cashblock_adcash.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.sudal.cashblock_adcash.databinding.LoginActivityBinding
import com.sudal.cashblock_adcash.PerfRepository

class LoginActivity : AppCompatActivity() {

    private val vb by lazy {
        LoginActivityBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        viewBind()
        // event
        vb.start.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        vb.loginButton.setOnClickListener {
            val loginID = vb.loginId.text.toString().trim()
            if (loginID.isNotEmpty()) {
                PerfRepository.userid = loginID
                viewBind()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewBind()
    }

    private fun viewBind() {
        vb.profileUserid.text = "Login-ID: ${PerfRepository.userid}"
        vb.profileContainer.isVisible = PerfRepository.userid.isNotEmpty()
        vb.logoutContainer.isVisible = PerfRepository.userid.isEmpty()
    }
}