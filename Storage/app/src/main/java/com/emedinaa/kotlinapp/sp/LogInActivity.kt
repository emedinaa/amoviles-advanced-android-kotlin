package com.emedinaa.kotlinapp.sp

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_log_in.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.R


class LogInActivity : AppCompatActivity() {

    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        ui()
    }

    private fun validateForm(): Boolean {
        username = eteUsername.text.toString()
        password = etePassword.text.toString()

        if (username.isNullOrEmpty()) {
            eteUsername.error = "Error campo username"
            return false
        }
        if (password.isNullOrEmpty()) {
            etePassword.error = "Error campo password"
            return false
        }
        return true
    }

    private fun gotoMain() {
        savePreferences()
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun savePreferences() {
        username?.let {
            PreferencesHelper.saveSession(this, it)
        }
    }

    private fun ui(){
        btnLogin.setOnClickListener {
            if(validateForm()){
                savePreferences()
                gotoMain()
            }
        }

    }
}
