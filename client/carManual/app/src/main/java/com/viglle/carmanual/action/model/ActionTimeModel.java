package com.viglle.carmanual.action.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/3.
 */
public class ActionTimeModel extends BaseActionModel{
    private String time;//时间(倒计时的时间,毫秒)
    public static final String TIME="time";

    public long getTime() {
        if(time==null||time.equals("")||time.equals("null")){
            return 0;
        }
        if(!AppUtil.isNumeric(time)){
            return 0;
        }
        return Long.parseLong(time);
    }

    public void setTime(String time) {
        this.time = time;
    }
}
