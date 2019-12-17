package com.emedinaa.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emedinaa.kotlinapp.data.Injection
import com.emedinaa.kotlinapp.model.User
import com.emedinaa.kotlinapp.viewmodel.LogInViewModel
import com.emedinaa.kotlinapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    companion object {
        const val TAG= "CONSOLE"
    }

    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(
            Injection.provideLogInRepository())).get(LogInViewModel::class.java)

        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
        viewModel.user.observe(this,isAuthenticationObserver)

        //events
        buttonLogIn.setOnClickListener {
            if(validateForm()){
                viewModel.logIn(usernameField(),passwordField())
            }
        }
    }

    private val isAuthenticationObserver = Observer<User> {
        Log.v(TAG, "isAuthenticationObserver $it")
        it?.let {itUser ->
            goToMainView(itUser)
        }

    }

    private val isViewLoadingObserver= Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility=if(it)View.VISIBLE else View.GONE
        viewLoading.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        Toast.makeText(this, "error $it",Toast.LENGTH_LONG).show()
    }


    private fun goToMainView(user: User?) {
        val bundle:Bundle= Bundle().apply {
            putSerializable("USER",user?:"")
        }
        startActivity(Intent(this,MainActivity::class.java).putExtras(bundle))
        //finish()
    }

    private fun usernameField():String {
        return editTextEmail.text.toString()
    }

    private fun passwordField():String {
        return editTextPassword.text.toString()
    }

    private fun validateForm(): Boolean {
        return editTextEmail.text.toString().isNotEmpty() && editTextPassword.text.toString().isNotEmpty()
    }

}
