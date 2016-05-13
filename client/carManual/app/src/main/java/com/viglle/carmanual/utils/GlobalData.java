package com.viglle.carmanual.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/11.
 * 保存全局变量
 */
public class GlobalData {
    static Map<String ,String> userInfo=new HashMap<>();
    public static void putDATA(String key,String value){
        if(key==null||key.equals("")||key.equals("null")){
            return;
        }
        if(userInfo==null){
            userInfo=new HashMap<>();
        }
        userInfo.put(key, value);
    }

    public static String getDATA(String key){
        if(key==null||key.equals("")||key.equals("null")){
            return "";
        }
        if(userInfo==null||userInfo.isEmpty()){
            return "";
        }
        return userInfo.get(key);
    }

    public static void login(){

    }

    public static void logout(){//退出登录之后,为安全起见,应当清除所有与用户有关的数据;
        if(userInfo==null){
            return;
        }
        if(!userInfo.isEmpty()){
            userInfo.clear();
        }
        userInfo=null;
    }
}
