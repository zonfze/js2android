package com.viglle.carmanual.validation;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/13.
 */
public abstract class BaseValidModel {
    private String valid_id;
    public static final String VALID_ID="valid_id";

    private String valid_msg;
    public static final String VALID_MSG="msg";

    public int getValid_id() {
        if(valid_id==null||valid_id.equals("")||valid_id.equalsIgnoreCase("null")){
            return -1;
        }
        if(!AppUtil.checkNumber(valid_id, "0+")){
            return -1;
        }
        return Integer.parseInt(valid_id);
    }

    public void setValid_id(String valid_id) {
        this.valid_id = valid_id;
    }

    public String getValid_msg() {
        if(valid_msg==null){
            return "";
        }
        return valid_msg;
    }

    public void setValid_msg(String valid_msg) {
        this.valid_msg = valid_msg;
    }
}
