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

//    private List<Integer>ref_ui=new ArrayList<>();
//    public static final String REF_UI="ref_ui";



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
