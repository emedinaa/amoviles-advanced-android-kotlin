package com.emedinaa.kotlinapp.login

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.data.DataCallback
import com.emedinaa.kotlinapp.data.DataInjector
import com.emedinaa.kotlinapp.data.PreferencesHelper
import com.emedinaa.kotlinapp.data.remote.LogInRaw
import com.emedinaa.kotlinapp.data.socket.SocketConstant
import com.emedinaa.kotlinapp.data.socket.SocketLogInRaw
import com.emedinaa.kotlinapp.data.socket.SocketManager
import com.emedinaa.kotlinapp.data.socket.SocketMapper
import com.emedinaa.kotlinapp.model.Cart
import com.emedinaa.kotlinapp.model.User
import com.emedinaa.kotlinapp.utils.Logger
import io.socket.client.Ack
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.layout_loading.view.*
import kotlinx.android.synthetic.main.layout_login_session.view.*
import kotlinx.android.synthetic.main.layout_login_view.view.*
import org.json.JSONException
import org.json.JSONObject

class LogInView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var logInRepository: LogInRepository = DataInjector.instance().provideLogInRepository()
    private var user: User? = null
    private var socket: Socket?=null
    private var socketManager: SocketManager=DataInjector.provideSocketManager()

    init {
        socket= socketManager.socket()

        addView(View.inflate(context, R.layout.layout_login_view, null))
        addView(View.inflate(context, R.layout.layout_login_session, null))
        addView(View.inflate(context, R.layout.layout_loading, null))

        layoutLogInSession.visibility = View.GONE
        layoutLogIn.visibility = View.VISIBLE
        layoutLoading.visibility = View.GONE

        buttonLogIn.setOnClickListener {
            logIn()
        }

        btnLogOut.setOnClickListener {
            showLogInView()
        }

        checkSession()
    }

    private fun checkSession() {
        user = PreferencesHelper.session(context)
        user?.let {
            Logger.v("user $it")
            showLogInViewSession()
        } ?: run {
            logOut()
        }
    }

    private fun logOut() {
        PreferencesHelper.signOut(context)
    }


    private fun logIn(){
        showLoadingView()
        val username:String= editTextEmail.text.toString().trim()
        val password:String= editTextPassword.text.toString().trim()

        logInRepository.logIn(username,password,object:DataCallback<User>{
            override fun onError(errorMessage: String, exception: Exception) {
                hideLoadingView()
                showError(errorMessage)
            }

            override fun onSuccess(data: User) {
                hideLoadingView()
                user= data
                startSocket()
                /*post {
                    textViewEmail.text= user?.username
                    showLogInViewSession()
                }*/
            }
        })
    }

    private val onConnect = Emitter.Listener { args ->
        Log.v("CONSOLE", "onConnect $args")
        socketLogIn()
    }

    private val onLogIn = Emitter.Listener { args ->
        Log.v("CONSOLE", "onLogIn $args")
    }


    private fun startSocket(){
        socket?.on(Socket.EVENT_CONNECT,onConnect)
        socket?.connect()
    }

    private fun socketLogIn(){
        socket?.on(SocketConstant.EMIT_LOGIN,onLogIn)

        Log.v("CONSOLE","socket "+socket?.connected() +" user "+user );
        if(socket?.connected()==true && user!=null) {
            // Sending an object
            val obj =  JSONObject()
            try {
                obj.put("user_id", user?.id)
            } catch (e:JSONException) {
                e.printStackTrace()
            }

            socket?.emit(SocketConstant.EMIT_LOGIN,obj,object :Ack{
                override fun call(vararg args: Any?) {
                    Log.v("CONSOLE", "EMIT LogIn $args")

                    val data = args[0] as JSONObject
                    val socketResponse = SocketMapper().convert(data)
                    Log.v("CONSOLE", "EMIT LogIn $socketResponse")
                    if (socketResponse.success) {
                        saveSession()
                        Cart.createOrder(user?.id?:"-1")
                        post {
                            saveSession()
                            showLogInViewSession()
                        }
                    } else {
                        post {
                            showMessage("Ocurrió un error : " + socketResponse.message)
                        }
                    }
                }
            })
        }

    }

    private fun saveSession() {
        user?.let {
            PreferencesHelper.saveUser(context, it)
        }
    }

    private fun showError(errorMessage:String){
        Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show()
    }

    private fun showLogInView(){
        layoutLogInSession.visibility= View.GONE
        layoutLogIn.visibility= View.VISIBLE
    }

    private fun showLogInViewSession(){
        layoutLogInSession.visibility= View.VISIBLE
        layoutLogIn.visibility= View.GONE

        textViewEmail.text= user?.username?.plus(" ").plus(user?.lastName)
    }

    private fun showLoadingView(){
        layoutLoading.visibility= View.VISIBLE
    }

    private fun hideLoadingView(){
        layoutLoading.visibility= View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}