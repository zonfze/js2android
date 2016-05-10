package com.viglle.carmanual.action.model;

import com.viglle.carmanual.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class ActionHttpModel extends BaseActionModel{
    private String url;
    public static final String URL="url";

    private List<Integer>ref_ui=new ArrayList<>();
    public static final String REF_UI="ref_ui";



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
