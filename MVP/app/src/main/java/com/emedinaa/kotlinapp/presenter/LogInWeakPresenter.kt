package com.emedinaa.kotlinapp.presenter

import com.emedinaa.kotlinapp.data.LogInRepository
import com.emedinaa.kotlinapp.data.OperationCallback
import com.emedinaa.kotlinapp.model.User
import com.emedinaa.kotlinapp.view.LogInContract
import java.lang.ref.WeakReference

class LogInWeakPresenter(private val repository: LogInRepository):LogInContract.Presenter{

    private lateinit var view:WeakReference<LogInContract.View>

    override fun setContractView(view :LogInContract.View){
        this.view= WeakReference(view)
        this.view.get()?.presenter=this
    }

    override fun logIn() {
        view.get()?.let {itView->
            if (itView.validateForm()) {
                itView.showLoadingView()
                repository.logIn(itView.usernameField(), itView.passwordField(), object :
                    OperationCallback {
                    override fun onError(obj: Any?) {
                        itView.hideLoadingView()
                        obj?.let {
                            if(it is String){
                                itView.showError(it)
                            }
                        }?:kotlin.run {
                            itView.showError("Ocurrío un error")
                        }
                    }

                    override fun onSuccess(obj: Any?) {
                        itView.hideLoadingView()
                        obj?.let {
                            if(it is User){
                                itView.goToMainView(it)
                            }
                        }
                    }
                })
            } else {

            }
        }
    }

}