package com.emedinaa.chefapp.data.socket;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class SocketMapper {

    private final String PARAM_SUCCESS="success";
    private final String PARAM_MESSAGE="message";

    public SocketResponse convert(JSONObject jsonObject){
        SocketResponse socketResponse= new SocketResponse();
        if(jsonObject==null)return socketResponse;

        boolean success=false;
        String message="";
        try {
            //success = jsonObject.getBoolean("success");
            //message = jsonObject.getString("message");
            success = jsonObject.getBoolean(PARAM_SUCCESS);
            message = jsonObject.getString(PARAM_MESSAGE);
        } catch (JSONException e) {
            Log.e("CONSOLE", e.getMessage());
        }
        socketResponse.setSuccess(success);
        socketResponse.setMessage(message);
        return socketResponse;
    }
}
