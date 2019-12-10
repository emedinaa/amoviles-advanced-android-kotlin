package com.emedinaa.chefapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.emedinaa.chefapp.model.entity.User;
import com.emedinaa.core.helpers.GsonHelper;


/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class PreferencesHelper {

    private static final String APP_PREFERENCES = "com.emedinaa.myrestaurant";
    private static final String PREFERENCES_SESSION = APP_PREFERENCES + ".session";
    private static final String PREFERENCES_USERNAME = APP_PREFERENCES + ".username";
    private static final String PREFERENCES_PASSWORD = APP_PREFERENCES + ".password";
    private static final String PREFERENCES_TOKEN = APP_PREFERENCES + ".token";

    public PreferencesHelper() {
        //no instance
    }

    public static void signOut(@NonNull Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        //editor.remove(PREFERENCES_USERNAME);
        //editor.remove(PREFERENCES_PASSWORD);
        editor.clear();
        editor.apply();
    }

    public static void saveUser(@NonNull Context context,User user) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_SESSION, new GsonHelper().objectToJSON(user).toString());
        editor.apply();
    }

    public static User session(@NonNull Context context) {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String  userStr= sharedPreferences.getString(PREFERENCES_SESSION,"");
        User user= new GsonHelper().jsonToObject(userStr,User.class);
        return user;
    }

    public static void saveSession(@NonNull Context context,String username,String token)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_USERNAME, username);
        editor.putString(PREFERENCES_TOKEN, token);
        editor.apply();
    }

    public static String getUserSession(@NonNull Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String username= sharedPreferences.getString(PREFERENCES_USERNAME,null);

        return username;
    }

    public static String getTokenSession(@NonNull Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String token= sharedPreferences.getString(PREFERENCES_TOKEN,null);

        return token;
    }

    public static boolean isSignedIn(@NonNull Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCES_USERNAME);
    }

    private static SharedPreferences.Editor getEditor(@NonNull Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }
}
