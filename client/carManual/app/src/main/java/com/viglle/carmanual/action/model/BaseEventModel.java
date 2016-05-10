package com.viglle.carmanual.action.model;

import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public abstract class BaseEventModel {
    private String eventType;
    public static final String EVENT_TYPE="event_type";

    private List<BaseActionModel> link=new ArrayList<>();
    public static final String LINK="link";

    public int getEventType() {
        if(eventType==null||eventType.equals("")||eventType.equals("null")){
            return 0;
        }
        if(!AppUtil.isNumeric(eventType)){
            return 0;
        }
        return Integer.parseInt(eventType);
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<BaseActionModel> getLink() {
        if(link==null){
            return new ArrayList<>();
        }
        return link;
    }

    public void setLink(List<BaseActionModel> link) {
        if(link==null||link.isEmpty()){
            return;
        }
        if(this.link==null){
            this.link=new ArrayList<>();
        }
        if(!this.link.isEmpty()){
            this.link.clear();
        }
        this.link.addAll(link);
    }
}
