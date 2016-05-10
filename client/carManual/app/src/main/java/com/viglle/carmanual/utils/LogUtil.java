package com.viglle.carmanual.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/4/28.
 */
public class LogUtil {
    public static  boolean isDebug=true;
    public static final String TAG="viglle_sdk";

    public static final int LEVEL_VERBOSE=0;
    public static final int LEVEL_DEBUG=1;
    public static final int LEVEL_INFO=2;
    public static final int LEVEL_WARM=3;
    public static final int LEVEL_ERROR=4;
//    public static final int LEVEL_ASSERT=5;

    public static void log_d(String tag,String msg){
        log(LEVEL_DEBUG,tag,msg);
    }

    public static void log_d(String tag,Exception e){
        log_d(tag, e.toString());
    }

    public static void log_d(String msg){
        log_d(TAG,msg);
    }

    public static void log_d(Exception e){
        log_d(TAG,e);
    }

    public static void log_i(String tag,String msg){
        log(LEVEL_INFO,tag,msg);
    }

    public static void log_i(String tag,Exception e){
        log_i(tag, e.toString());
    }

    public static void log_i(String msg){
        log_i(TAG, msg);
    }

    public static void log_i(Exception e){
        log_i(TAG, e);
    }

    public static void log_w(String tag,String msg){
        log(LEVEL_WARM,tag,msg);
    }

    public static void log_w(String tag,Exception e){
        log_w(tag, e.toString());
    }

    public static void log_w(String msg){
        log_w(TAG, msg);
    }

    public static void log_w(Exception e){
        log_w(TAG, e);
    }

    public static void log_e(String tag,String msg){
        log(LEVEL_ERROR,tag,msg);
    }

    public static void log_e(String tag,Exception e){
        log_e(tag, e.toString());
    }

    public static void log_e(String msg){
        log_e(TAG, msg);
    }

    public static void log_e(Exception e){
        log_e(TAG, e);
    }


    public static void log(int level,String tag,String msg){
        if(!isDebug){
           return;
        }
        switch (level){
            case LEVEL_VERBOSE:
                Log.v(tag,msg);
                break;
            case LEVEL_DEBUG:
                Log.d(tag, msg);
                break;
            case LEVEL_INFO:
                Log.i(tag, msg);
                break;
            case LEVEL_WARM:
                Log.w(tag, msg);
                break;
            case LEVEL_ERROR:
                Log.e(tag, msg);
                break;
//            case LEVEL_ASSERT:
//                Log.wtf(tag,msg);
//                break;
            default:
                Log.d(tag,msg);
        }
    }
}
