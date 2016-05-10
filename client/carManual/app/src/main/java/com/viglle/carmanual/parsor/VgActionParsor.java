package com.viglle.carmanual.parsor;

import com.viglle.carmanual.action.ActionType;
import com.viglle.carmanual.action.model.ActionCloseNewModel;
import com.viglle.carmanual.action.model.ActionHttpModel;
import com.viglle.carmanual.action.model.ActionToastModel;
import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.action.model.BaseEventModel;
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


    public static List<BaseActionModel> parsorLink(JSONObject jsonObject)throws JSONException{
        JSONArray array=jsonObject.getJSONArray(BaseEventModel.LINK);
        List<BaseActionModel> list = new ArrayList<>();
        if(array==null||array.length()<1){
            return list;
        }

        for (int i=0;i<array.length();i++){
            JSONObject obj=array.getJSONObject(i);
            list.add(parsorActionModel(obj));
        }
        return list;
    }

    public static BaseActionModel parsorActionModel(JSONObject jsonObject) throws JSONException {
        String actionTypeStr=jsonObject.getString(BaseActionModel.ACTION_TYPE);
        int actionType=0;
        try {
            actionType =Integer.parseInt(actionTypeStr);
        }catch (Exception  e){
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

                break;
            case ActionType.ACTION_CLOSE_PANEL:

                break;
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
        JSONArray array=json.getJSONArray(ActionHttpModel.REF_UI);
        List<String> list = new ArrayList<>();
        for(int i=0;i<array.length();i++){
            list.add((String)array.get(i));
        }
        return list;
    }

    private static List<TwoValues<String,String>> parsorParams(JSONObject json) throws JSONException {
        JSONArray array=json.getJSONArray(BaseActionModel.PARAMS);
        List<TwoValues<String,String>> list = new ArrayList<>();
        for(int i=0;i<array.length();i++){
            String as=array.getString(i);
            if(as!=null&&!as.equals("")&&as.contains("=")){
                String [] ass=as.split("=");
                if(ass.length>=2){
                    list.add(new TwoValues<String, String>(ass[0],ass[1]));
                }
            }
        }

        return list;
    }
}
