package com.emedinaa.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.data.Injection
import com.emedinaa.kotlinapp.model.User
import com.emedinaa.kotlinapp.presenter.LogInPresenter
import com.emedinaa.kotlinapp.presenter.LogInWeakPresenter
import com.emedinaa.kotlinapp.view.LogInContract
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity(), LogInContract.View {

    override lateinit var presenter: LogInContract.Presenter
    lateinit var weakPresenter:LogInContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        presenter= LogInPresenter(this, Injection.provideLogInRepository())

        weakPresenter= LogInWeakPresenter(Injection.provideLogInRepository())
        weakPresenter.setContractView(this)

        //events
        buttonLogIn.setOnClickListener {
            //presenter.logIn()
            weakPresenter.logIn()
        }
    }

    override fun showLoadingView() {
        viewLoading.visibility= View.VISIBLE
    }

    override fun hideLoadingView() {
        viewLoading.visibility= View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(this, "error $error",Toast.LENGTH_LONG).show()
    }

    override fun goToMainView(user: User?) {
        val bundle:Bundle= Bundle().apply {
            putSerializable("USER",user?:"")
        }
        startActivity(Intent(this,MainActivity::class.java).putExtras(bundle))
        finish()
    }

    override fun usernameField():String {
        return editTextEmail.text.toString()
    }

    override fun passwordField():String {
        return editTextPassword.text.toString()
    }

    override fun validateForm(): Boolean {
        return editTextEmail.text.toString().isNotEmpty() && editTextPassword.text.toString().isNotEmpty()
    }

}
