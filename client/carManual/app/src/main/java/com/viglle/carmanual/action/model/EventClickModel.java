package com.viglle.carmanual.action.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/4.
 */
public class EventClickModel extends BaseEventModel{
    private String view_id;

    public int getView_id() {
        if(view_id==null||view_id.equals("")||view_id.equals("null")){
            return 0;
        }
        if(!AppUtil.isNumeric(view_id)){
            return 0;
        }
        return Integer.parseInt(view_id);
    }

    public void setView_id(String view_id) {
        this.view_id = view_id;
    }
}
