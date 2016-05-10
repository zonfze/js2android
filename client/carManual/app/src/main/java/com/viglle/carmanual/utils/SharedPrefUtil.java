package com.viglle.carmanual.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/4/21.
 */
public class SharedPrefUtil {
    public static  String SDK_FILE="vg_sdk_shared";

    private  static SharedPrefUtil instance;
    private static SharedPreferences sharedPreferences;
    private SharedPrefUtil(){

    }
    public static SharedPrefUtil getInstance(Context ctx){
        if(instance==null){
            instance=new SharedPrefUtil();
            sharedPreferences=ctx.getSharedPreferences(SDK_FILE,Context.MODE_PRIVATE);
        }
        return instance;
    }

    public void saveProperty(String key,String value){
        sharedPreferences.edit().putString(key,value).apply();
    }

    public String getProperty(String key,String defaultValue){
        return sharedPreferences.getString(key,defaultValue);
    }
}
