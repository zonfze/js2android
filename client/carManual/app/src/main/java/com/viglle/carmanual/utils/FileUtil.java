package com.viglle.carmanual.utils;

import android.os.Environment;

/**
 * Created by Administrator on 2016/4/18.
 */
public class FileUtil {
    public static String getChacheImageDir(){
        boolean isExists=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(isExists){
            return Environment.getExternalStorageDirectory().getAbsolutePath()+FileDirConstant.imageCacheDir;
        }else{
            return "";
        }
    };
}
