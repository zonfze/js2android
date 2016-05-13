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

    private List<Integer>ref_ui=new ArrayList<>();
    public static final String REF_UI="ref_ui";

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
        if(params==null||params.isEmpty()){
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

    public List<Integer> getRef_ui() {
        return ref_ui;
    }

    public void setRef_ui(List<String> refui) {
        if(refui==null||refui.isEmpty()){
            return;
        }
        if(!ref_ui.isEmpty()){
            ref_ui.clear();
        }
        for(int i = 0;i<refui.size();i++) {
            String item=refui.get(i);
            if(item==null ||item.equals("")||item.equals("null")){
                new Exception("refui 不能为无效"+item);
                return;
            }
            if(!AppUtil.checkNumber(item, "")){
                return;
            }
            ref_ui.add(Integer.parseInt(item));
        }
    }
}
