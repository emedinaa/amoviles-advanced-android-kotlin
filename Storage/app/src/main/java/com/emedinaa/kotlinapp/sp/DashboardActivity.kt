package com.emedinaa.kotlinapp.sp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnLogOut.setOnClickListener {
            //borrar sesión
            //ir al login
            //destruir activity
            clearSession()
            goToLogIn()
            finish()
        }
    }

    private fun goToLogIn(){
        val intent= Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }


    private fun clearSession(){
        PreferencesHelper.clearSession(this)
    }
}
