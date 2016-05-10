package com.viglle.carmanual.action.model;

import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.net.TwoValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public abstract class BaseActionModel {
    private String actionType;//action的类型
    public static final String ACTION_TYPE="actionType";

    private List<TwoValues<String,String>> params=new ArrayList<>();
    public static final String PARAMS="params";

    public int getActionType() {
        if(actionType==null||actionType.equals("")||actionType.equals("null")){
            return 0;
        }
        if(!AppUtil.isNumeric(actionType)){
            return 0;
        }
        return Integer.parseInt(actionType);
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public List<TwoValues<String,String>> getParams() {
        if(params==null){
            return new ArrayList<>();
        }
        return params;
    }

    public void setParams(List<TwoValues<String,String>> params) {
        if(params==null){
            return;
        }
        if(this.params==null){
            this.params=new ArrayList<>();
        }
        if(!this.params.isEmpty()){
            this.params.clear();
        }
        this.params.addAll(params);

    }
}
