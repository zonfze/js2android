package com.viglle.carmanual.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.viglle.carmanual.widget.model.BaseViewModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

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

    public BaseViewModel getBaseViewModel(String key){
       return  (BaseViewModel)getObject(key,BaseViewModel.class);
    }
    public void saveBaseViewModel(String key,BaseViewModel model){
        if(key==null||key.equals("")){
            LogUtil.log_e(new Exception("key is not allowed to be null"));
            return;
        }
        setObject(key,model);
    }

    /**
     * 针对复杂类型存储<对象>
     *
     * @param key
     */
    public void setObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {

            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(),
                    Base64.DEFAULT));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, objectVal);
            editor.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(String key, Class<T> clazz) {
        if (sharedPreferences.contains(key)) {
            String objectVal = sharedPreferences.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
