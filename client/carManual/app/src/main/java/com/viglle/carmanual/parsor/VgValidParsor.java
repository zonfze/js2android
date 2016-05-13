package com.viglle.carmanual.parsor;

import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.validation.BaseValidModel;
import com.viglle.carmanual.validation.ValidLengthModel;
import com.viglle.carmanual.validation.ValidMatcherModel;
import com.viglle.carmanual.validation.ValidRequireModel;
import com.viglle.carmanual.validation.ValidType;
import com.viglle.carmanual.widget.model.BaseViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class VgValidParsor {
    public static List<BaseValidModel> parsorValidLink(JSONObject object) throws JSONException {

        if (!checkObj(object)) return null;
        JSONArray array=object.getJSONArray(BaseViewModel.VALID_LINK);
        if(array==null||array.length()<1){
            return null;
        }
        List<BaseValidModel> list = new ArrayList<>();
        for (int i=0;i<array.length();i++){
            JSONObject obj=array.getJSONObject(i);
            BaseValidModel model=parsorValidMode(obj);
            if(model!=null){
                list.add(model);
            }
        }
        return list;
    }

    private static BaseValidModel parsorValidMode(JSONObject object) throws JSONException {
        if (!checkObj(object)) return null;
        String validId=object.getString(BaseValidModel.VALID_ID);
        if(validId==null||validId.equals("")||validId.equalsIgnoreCase("null")){
            return null;
        }
        if(!AppUtil.isNumeric(validId)){
            return null;
        }
        int id=-1;
        try {
            id =Integer.parseInt(validId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        switch (id){
            case ValidType.REQUIRE:
                ValidRequireModel requireModel=new ValidRequireModel();
                parsorCommon(requireModel,object);

                return requireModel;
            case ValidType.LENGT:
                ValidLengthModel lengthModel=new ValidLengthModel();
                parsorCommon(lengthModel,object);
                lengthModel.setMin(object.getString(ValidLengthModel.MIN));
                lengthModel.setMax(object.getString(ValidLengthModel.MAX));
                return lengthModel;
            case ValidType.MATCHER:
                ValidMatcherModel matcherModel=new ValidMatcherModel();
                parsorCommon(matcherModel,object);
                matcherModel.setRule(object.getString(ValidMatcherModel.RULE));
                return matcherModel;

            default:
                return null;
        }

    }

    private static void parsorCommon(BaseValidModel model,JSONObject oj) throws JSONException {
        model.setValid_id(oj.getString(BaseValidModel.VALID_ID));
        model.setValid_msg(oj.getString(BaseValidModel.VALID_MSG));
    }


    private static boolean checkObj(JSONObject json) {
        if(json==null||json.toString().equals("")||json.toString().equals("null")||json.toString().equals("{}")){//判断参数是否合法
            return false;
        }
        return true;
    }
}
