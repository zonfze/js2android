package com.viglle.carmanual.parsor;

import com.viglle.carmanual.action.ActionType;
import com.viglle.carmanual.action.model.ActionCloseNewModel;
import com.viglle.carmanual.action.model.ActionCloseUIModel;
import com.viglle.carmanual.action.model.ActionHttpModel;
import com.viglle.carmanual.action.model.ActionNewUIModel;
import com.viglle.carmanual.action.model.ActionToastModel;
import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.action.model.BaseEventModel;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.net.TwoValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class VgActionParsor {


    public static List<BaseActionModel> parsorActionLink(JSONObject jsonObject)throws JSONException{
        JSONArray array=jsonObject.getJSONArray(BaseEventModel.ACTION_LINK);
        List<BaseActionModel> list = new ArrayList<>();
        if (!checkJSONArray(array)) return null;
        for (int i=0;i<array.length();i++){
            JSONObject obj=array.getJSONObject(i);
            BaseActionModel actionModel=parsorActionModel(obj);
            if(actionModel!=null){
                list.add(actionModel);
            }
        }
        return list;
    }

    public static BaseActionModel parsorActionModel(JSONObject jsonObject) throws JSONException {
        if (!checkObj(jsonObject)) return null;
        String actionTypeStr=jsonObject.getString(BaseActionModel.ACTION_TYPE);
        int actionType=0;
        if(actionTypeStr==null){//判断获取到的字符串是否合法,避免空指针
            return null;
        }
        if(!AppUtil.isNumeric(actionTypeStr)){//校验字符串是否是由数字组成;避免字符串转数字的时异常
            return null;
        }
        try {
            actionType =Integer.parseInt(actionTypeStr);//字符串转数字
        }catch (Exception  e){//捕获转化异常;避免转换错误时程序崩溃
            e.printStackTrace();
            return null;
        }
        switch (actionType){
            case ActionType.ACTION_SHOW_TOAST:
                ActionToastModel  modelToast=new ActionToastModel();
                modelToast.setText(jsonObject.getString(ActionToastModel.TEXT));
                modelToast.setActionType(actionTypeStr);
                modelToast.setParams(parsorParams(jsonObject));
                return modelToast;
            case ActionType.ACTION_SHOW_SNACKBAR:

                break;
            case ActionType.ACTION_OBTAIN_PARAM_FROM_SDCARD:

                break;
            case ActionType.ACTION_SAVE_PARAM_TO_SDCARD:

                break;
            case ActionType.ACTION_OBTAIN_PARAM_FROM_VIEW:

                break;
            case ActionType.ACTION_UPDATE_VIEW:

                break;
            case ActionType.ACTION_NEW_PANEL:
                ActionNewUIModel uiModel=new ActionNewUIModel();
                uiModel.setUrl(jsonObject.getString(ActionNewUIModel.URL));
                uiModel.setActionType(actionTypeStr);
                uiModel.setParams(parsorParams(jsonObject));
                return uiModel;
            case ActionType.ACTION_CLOSE_PANEL:
                ActionCloseUIModel closeUIModel=new ActionCloseUIModel();
                closeUIModel.setActionType(actionTypeStr);
                closeUIModel.setParams(parsorParams(jsonObject));
                return closeUIModel;
            case ActionType.ACTION_CLOSE_AND_NEW_PANEL:
                ActionCloseNewModel closeNewModel=new ActionCloseNewModel();
                closeNewModel.setUrl(jsonObject.getString(ActionCloseNewModel.URL));
                closeNewModel.setActionType(actionTypeStr);
                closeNewModel.setParams(parsorParams(jsonObject));
                return closeNewModel;
            case ActionType.ACTION_SEND_HTTP_REQUEST:
                ActionHttpModel  modelHttp=new ActionHttpModel();
                modelHttp.setUrl(jsonObject.getString(ActionHttpModel.URL));
                modelHttp.setActionType(actionTypeStr);
                modelHttp.setRef_ui(parsorRefUi(jsonObject));
                modelHttp.setParams(parsorParams(jsonObject));
                return modelHttp;
            case ActionType.ACTION_COUNT_TIMER:

                break;
            case ActionType.ACTION_COUNT_DOWN_TIMER:

                break;
            case ActionType.ACTION_SHOW_DIALOG:

                break;
            case ActionType.ACTION_DIMISS_DIALOG:

                break;

        }

        return null;
    }

    private static List<String> parsorRefUi(JSONObject json) throws JSONException {
        List<String> list = new ArrayList<>();
        if (!checkObj(json)) return null;
        JSONArray array=json.getJSONArray(ActionHttpModel.REF_UI);
        if (!checkJSONArray(array)) return null;
        for(int i=0;i<array.length();i++){
            list.add((String)array.get(i));
        }
        return list;
    }

    private static List<TwoValues<String,String>> parsorParams(JSONObject json) throws JSONException {
        List<TwoValues<String,String>> list = new ArrayList<>();
        if (!checkObj(json)) return null;
        JSONArray array=json.getJSONArray(BaseActionModel.PARAMS);
        if (!checkJSONArray(array)) return null;
        for(int i=0;i<array.length();i++){
            String as=array.getString(i);
            if(as!=null&&!as.equals("")&&as.contains("=")){//避免空指针异常
                String [] ass=as.split("=");
                if(ass.length>=2){//避免数组越界异常
                    list.add(new TwoValues<String, String>(ass[0],ass[1]));
                }
            }
        }

        return list;
    }

    private static boolean checkJSONArray(JSONArray array) {
        if(array==null||array.length()<1){//避免空指针异常
            return false;
        }
        return true;
    }

    private static boolean checkObj(JSONObject json) {
        if(json==null||json.toString().equals("")||json.toString().equals("null")||json.toString().equals("{}")){//判断参数是否合法
            return false;
        }
        return true;
    }
}
