package com.emedinaa.kotlinapp.presenter

import com.emedinaa.kotlinapp.data.DataResult
import com.emedinaa.kotlinapp.data.LogInRepository
import com.emedinaa.kotlinapp.data.OperationCallback
import com.emedinaa.kotlinapp.model.User
import com.emedinaa.kotlinapp.view.LogInContract

class LogInPresenter(val view: LogInContract.View, private val repository: LogInRepository):LogInContract.Presenter{

    init {
        view.presenter=this
    }

    override fun logIn() {
        if (view.validateForm()) {
            view.showLoadingView()
            repository.logIn(view.usernameField(), view.passwordField(), object :
                OperationCallback {
                override fun onError(obj: Any?) {
                    view.hideLoadingView()
                    obj?.let {
                        if(it is String){
                            view.showError(it)
                        }
                    }?:kotlin.run {
                        view.showError("Ocurrío un error")
                    }
                }

                override fun onSuccess(obj: Any?) {
                    view.hideLoadingView()
                    obj?.let {
                        if(it is User){
                            view.goToMainView(it)
                        }
                    }
                }
            })
        }
    }

    override fun logInDR() {
        if (view.validateForm()) {
            view.showLoadingView()

            repository.logInDR(view.usernameField(), view.passwordField()){
                when(it){
                    is DataResult.Success ->{
                        view.hideLoadingView()
                        view.goToMainView(it.data)
                    }

                    is DataResult.InvalidUserOrPw ->{
                        view.hideLoadingView()
                        view.showError(it.message)
                    }

                    is DataResult.Failure ->{
                        view.hideLoadingView()
                        view.showError(it.e.message?:"Ocurrió un error")
                    }
                }
            }
        }
    }

    override fun setContractView(view: LogInContract.View) {}
}