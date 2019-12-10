package com.emedinaa.kotlinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emedinaa.kotlinapp.data.OperationCallback
import com.emedinaa.kotlinapp.model.AuthenticationDataSource
import com.emedinaa.kotlinapp.model.User

class LogInViewModel(private val repository: AuthenticationDataSource):ViewModel(){

    private var _user = MutableLiveData<User>().apply { value = null }
    val user:LiveData<User> = _user

    private val _isViewLoading= MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    fun logIn(username:String,password:String){
        _isViewLoading.postValue(true)
        repository.logIn(username,password,object :OperationCallback{
            override fun onError(obj: Any?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( obj)
            }

            override fun onSuccess(mUser: User?) {
                _isViewLoading.postValue(false)
                _user.value= mUser
            }
        })
    }
}