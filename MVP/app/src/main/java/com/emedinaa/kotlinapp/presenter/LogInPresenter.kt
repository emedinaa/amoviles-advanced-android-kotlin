package com.emedinaa.kotlinapp.presenter

import com.emedinaa.kotlinapp.data.LogInRepository
import com.emedinaa.kotlinapp.data.OperationCallback
import com.emedinaa.kotlinapp.model.User
import com.emedinaa.kotlinapp.view.LogInContract

class LogInPresenter(val view: LogInContract.View, val repository: LogInRepository):LogInContract.Presenter{

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
        } else {

        }
    }

    override fun setContractView(view: LogInContract.View) {}
}