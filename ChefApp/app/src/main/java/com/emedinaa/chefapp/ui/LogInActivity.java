package com.emedinaa.chefapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.emedinaa.chefapp.R;
import com.emedinaa.chefapp.data.DataInjector;
import com.emedinaa.chefapp.data.callback.DataCallback;
import com.emedinaa.chefapp.data.local.PreferencesHelper;
import com.emedinaa.chefapp.data.socket.SocketConstant;
import com.emedinaa.chefapp.data.socket.SocketManager;
import com.emedinaa.chefapp.data.socket.SocketMapper;
import com.emedinaa.chefapp.data.socket.SocketResponse;
import com.emedinaa.chefapp.model.entity.User;
import com.emedinaa.chefapp.model.interactors.UserRestInteractor;
import com.emedinaa.core.ui.BasicActivity;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Ack;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class LogInActivity extends BasicActivity {

    private EditText editTextEmail, editTextPassword;
    private View buttonLogIn;

    private SocketManager socketManager;
    private Socket socket;
    private String email,password;
    private User user=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        socket = DataInjector.getInstance().socketManager().socket();
        socketManager = DataInjector.getInstance().socketManager();
        ui();
    }

    private void ui() {
        editTextEmail= findViewById(R.id.editTextEmail);
        editTextPassword= findViewById(R.id.editTextPassword);
        buttonLogIn= findViewById(R.id.buttonLogIn);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    logIn();
                }
            }
        });

        //test

        editTextEmail.setText("kelvin.ca91@gmail.com");
        editTextPassword.setText("123456");
    }

    private boolean validate(){
        email= editTextEmail.getText().toString().trim();
        password= editTextPassword.getText().toString().trim();

        if(email.isEmpty()) return false;
        if(password.isEmpty()) return false;

        return true;
    }
    private void logIn(){
        //logInLayout.setVisibility(View.GONE);
        //showLoading();
        UserRestInteractor userRestInteractor= new UserRestInteractor();
        userRestInteractor.logIn(email, password, new DataCallback() {
            @Override
            public void onSuccess(Object object) {
                user= (User)(object);
                PreferencesHelper.saveSession(LogInActivity.this,email,user.getToken());
                startSocket();
            }

            @Override
            public void onFailure(Exception exception) {
                //hideLoading();
                //logInLayout.setVisibility(View.VISIBLE);
                showMessage(exception.getMessage());
            }
        });
    }

    private Emitter.Listener onConnect= new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.v("CONSOLE", "onConnect "+args);
            socketLogIn();
        }
    };

    private void socketLogIn(){

        Log.v("CONSOLE","socket "+socket.connected() +" user "+user );
        if(socket.connected() && user!=null){
            // Sending an object
            JSONObject obj = new JSONObject();
            try {
                obj.put("user_id", user.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            socketManager.emit(SocketConstant.EMIT_LOGIN, obj, new Ack() {
                @Override
                public void call(Object... args) {
                    Log.v("CONSOLE", "EMIT LogIn "+args);

                    JSONObject data = (JSONObject) args[0];
                    final SocketResponse socketResponse= new SocketMapper().convert(data);
                    Log.v("CONSOLE", "EMIT LogIn "+socketResponse.toString());
                    if(socketResponse.isSuccess()){
                        saveSession();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLoading();
                                goToDashboard();
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLoading();
                                showMessage("Ocurrió un error : "+socketResponse.getMessage());
                            }
                        });
                    }
                }
            });
        }
    }
    private void goToDashboard(){
        Bundle bundle= new Bundle();
        Intent intent= new Intent(this,DashboardActivity.class);
        nextActivity(intent,bundle,true);
        //overridePendingTransition(0,0);
    }

    private void startSocket(){
        socket.on(Socket.EVENT_CONNECT,onConnect);
        socket.connect();
    }

    private void saveSession(){
        PreferencesHelper.saveUser(this,user);
    }
    private void showLoading(){}
    private void hideLoading(){}
}
