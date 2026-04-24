package com.example.vodocanalmobileapp.api;

import android.content.Context;
import android.content.SharedPreferences;

public class ServerConfig {

    private static final String PREFS = "server_settings";
    private static final String KEY_IP = "server_ip";

    public static String getBaseUrl(Context context){

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        String ip = prefs.getString(KEY_IP, "10.0.2.2");

        return "http://" + ip + ":5000/";
    }

    public static void saveServerIp(Context context, String ip){

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        prefs.edit().putString(KEY_IP, ip).apply();
    }

}