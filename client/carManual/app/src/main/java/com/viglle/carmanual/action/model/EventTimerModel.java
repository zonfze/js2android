package com.viglle.carmanual.action.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/4.
 */
public class EventTimerModel extends BaseEventModel{
    private String time;
    public static final String TIME="time";

    public long getTime() {
        if(time==null||time.equals("")||time.equals("null")){
            return 0L;
        }
        if(!AppUtil.isNumeric(time)){
            return 0L;
        }
        return Long.parseLong(time);
    }

    public void setTime(String time) {

        this.time = time;
    }
}
