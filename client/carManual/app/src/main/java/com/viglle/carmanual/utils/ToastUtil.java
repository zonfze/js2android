package com.viglle.carmanual.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/4.
 */
public class ToastUtil {
    public static void showToast(Context context,String msg){
        if(msg==null||msg.equals("")||msg.equals("null")){
            return;
        }
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static void showToast(Context context,int resid){
        String msg=context.getResources().getString(resid);
        showToast(context,msg);
    }
}
