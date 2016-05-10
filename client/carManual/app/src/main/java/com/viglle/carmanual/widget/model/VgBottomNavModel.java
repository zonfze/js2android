package com.viglle.carmanual.widget.model;

import com.viglle.carmanual.widget.entity.NavItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
public class VgBottomNavModel extends BaseViewModel{

    public static  final String DATAS="datas";
    private List<NavItemModel> datas = new ArrayList<>();

    public List<NavItemModel> getDatas() {
        return datas;
    }

    public void setDatas(List<NavItemModel> datas) {
        if(datas==null||datas.isEmpty()){
            return;
        }
        if(!this.datas.isEmpty()){
            this.datas.clear();
        }
        this.datas.addAll(datas);
    }

    public static List<NavItemModel> parseDatas(JSONObject jsonObject) throws JSONException {
        List<NavItemModel> list = new ArrayList<>();
        JSONArray array = jsonObject.getJSONArray(VgBottomNavModel.DATAS);
        for(int i=0;i<array.length();i++){
            JSONObject object = array.getJSONObject(i);
            NavItemModel bean=new NavItemModel();
            bean.setLabelId(object.getString(NavItemModel.LABEL_ID));
            bean.setFocusImage(object.getString(NavItemModel.FOCUS_IMAGE));
            bean.setUnFocusImage(object.getString(NavItemModel.UN_FOCUS_IMAGE));
            bean.setMsgNum(object.getString(NavItemModel.MSG_NUM));
            bean.setTitle(object.getString(NavItemModel.TAB_TEXT));
            list.add(bean);
        }
        return list;
    }
}
