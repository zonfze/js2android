package com.viglle.carmanual.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.viglle.carmanual.utils.net.TwoValues;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/5.
 */
public class AppUtil {

//    public static String FIRST_URL="http://192.168.16.198:8082/?code=welcome";
    public static String FIRST_URL="http://192.168.16.198:8083/?code=welcomeUI";

    public static int screenWidth=0;//静态变量保存屏幕宽度
    public static int screenHeight=0;//静态变量保存屏幕高度
    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        if (isNum.matches()) {
            return true;
        }
        return false;
    }

    public static boolean isMatcherStr(String rex,String data){
        Pattern pattern = Pattern.compile(rex);
        Matcher isNum = pattern.matcher(data);

        if (isNum.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 检查整数
     * @param num
     * @param type "0+":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数
     * @return
     */
    public static boolean checkNumber(String num,String type){
        String eL = "";
        if(type.equals("0+"))eL = "^\\d+$";//非负整数
        else if(type.equals("+"))eL = "^\\d*[1-9]\\d*$";//正整数
        else if(type.equals("-0"))eL = "^((-\\d+)|(0+))$";//非正整数
        else if(type.equals("-"))eL = "^-\\d*[1-9]\\d*$";//负整数
        else eL = "^-?\\d+$";//整数
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(num);
        boolean b = m.matches();
        return b;
    }


    public static int dip2px(Context ctx,float dpValue){
        final float density=ctx.getResources().getDisplayMetrics().density;
        Log.i("density","density="+density);
        return (int)(dpValue*density+0.5f);
    }

    public static int px2dp(Context ctx,float pxValue){
        final float density=ctx.getResources().getDisplayMetrics().density;
        return (int)(pxValue/density+0.5f);
    }

    public static float getDensity(Context ctx){
        final float density=ctx.getResources().getDisplayMetrics().density;

        return density;
    }

    /**
     * 由于setTextSize传递的是sp单位;所以单独计算转换
     * @param ctx
     * @param pxValue
     * @return
     */
    public static int px2sp(Context ctx,int pxValue){
        final float density=ctx.getResources().getDisplayMetrics().scaledDensity;
        Log.i("scaledDensity","scaledDensity="+density);
        return (int)((pxValue/3)-(3-density)+0.5f);
//        return (int)((pxValue/3)-(3-density)+0.5f);
    }

    public static int calWidth(Context ctx,int pxValue){
        final float density=ctx.getResources().getDisplayMetrics().density;
        return (int)((pxValue/3)*density+0.5f);
    }

    public static int calHeight(Context ctx,int pxValue){
        final float density=ctx.getResources().getDisplayMetrics().density;
        return (int)((pxValue/3)*density+0.5f);
    }

    /**
     * get the screen width
     * @param ctx
     * @return
     */
    public static int getScreenWidth(Context ctx){
        if(screenWidth<=0){
            screenWidth=ctx.getResources().getDisplayMetrics().widthPixels;
        }
       return  screenWidth;
    }
    /**
     * get the screen height
     * @param ctx
     * @return
     */
    public static int getScreenHeight(Context ctx){
        if(screenHeight<=0){
            screenHeight=ctx.getResources().getDisplayMetrics().heightPixels;
        }
        return  screenHeight;
    }

    public static boolean isColor(String colorStr){
        String dex6="^#[0-9a-fA-F]{6}$";
        String dex8="^#[0-9a-fA-F]{8}$";
        Pattern pattern6=Pattern.compile(dex6);
        Matcher matcher6 = pattern6.matcher(colorStr);
        if(matcher6.find()){
            return true;
        }
        Pattern pattern8=Pattern.compile(dex8);
        Matcher matcher8 = pattern8.matcher(colorStr);
        if(matcher8.find()){
            return true;
        }
      return  false;
    }

    /**
     * 构建　GET　请求参数
     * @param url
     * @param params
     * @return
     */
    public static String buildCacheKey(String url,List<TwoValues<String,String>> params){
        if(params==null||params.isEmpty()){
            return "";
        }
        String limit="?";
        StringBuffer stringBuffer=new StringBuffer();
        for(TwoValues<String,String> model:params){
            stringBuffer.append(limit).append(model.key).append("=").append(model.value);
            limit="&";
        }
//        Log.i("getParamStr", "getParamStr=" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    /**
     * 获取手机的IMEI
     *
     * @param context
     * @return
     */
    public static String IMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = telephonyManager.getDeviceId();
        return IMEI;
    }

}
