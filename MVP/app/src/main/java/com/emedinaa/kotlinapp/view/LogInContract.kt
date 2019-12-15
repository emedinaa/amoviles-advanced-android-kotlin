package com.emedinaa.kotlinapp.view

import com.emedinaa.kotlinapp.model.User

interface LogInContract {

    interface View: BaseView<Presenter> {
        fun showLoadingView()
        fun hideLoadingView()
        fun goToMainView(user: User?)
        fun showError(error:String)
        fun usernameField():String
        fun passwordField():String
        fun validateForm():Boolean
    }

    interface Presenter{
        fun logIn()
        fun logInDR()
        fun setContractView(view :View)
    }
}