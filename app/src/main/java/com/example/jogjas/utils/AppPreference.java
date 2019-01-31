package com.example.jogjas.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    public static String PrefName = "JOGJAS";
    public static String LOGIN = "login";
    public static String LOGOUT = "logout";

    public static void setPreference (Context context, String key, String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PrefName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,data);
        editor.apply();
    }

    public static String getPreference(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(PrefName,Context.MODE_PRIVATE);
        return preferences.getString(key,null);
    }

    public static void setPreferenceBoolean (Context context, String key, boolean data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PrefName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,data);
        editor.apply();
    }

    // ********* Dont use this method again *********
    public static boolean getPreferenceBoolean(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(PrefName,Context.MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }
}
