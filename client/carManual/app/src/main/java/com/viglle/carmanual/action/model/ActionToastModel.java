package com.viglle.carmanual.action.model;

/**
 * Created by Administrator on 2016/5/3.
 */
public class ActionToastModel extends BaseActionModel{
    private String text;
    public static final String TEXT="text";

    public String getText() {
        if(text==null){
            return "";
        }
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
