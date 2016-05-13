package com.viglle.carmanual.widget.model;

import com.viglle.carmanual.widget.entity.BottomNavPoupItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class VgBottomNavPupopLayoutModel extends BaseViewModel{

    public static  final String DATAS="datas";
    private List<BottomNavPoupItemModel> datas = new ArrayList<>();

    public List<BottomNavPoupItemModel> getDatas() {
        return datas;
    }

    public void setDatas(List<BottomNavPoupItemModel> datas) {
        if(datas==null||datas.isEmpty()){
            return;
        }
        if(!this.datas.isEmpty()){
            this.datas.clear();
        }
        this.datas.addAll(datas);
    }

    public static List<BottomNavPoupItemModel> parseDatas(JSONObject jsonObject) throws JSONException {
        List<BottomNavPoupItemModel> list = new ArrayList<>();
        JSONArray array = jsonObject.getJSONArray(VgBottomNavModel.DATAS);
        for(int i=0;i<array.length();i++){
            JSONObject object = array.getJSONObject(i);
            BottomNavPoupItemModel bean=new BottomNavPoupItemModel();
            bean.setLabelId(object.getString(BottomNavPoupItemModel.LABEL_ID));
            bean.setUrl(object.getString(BottomNavPoupItemModel.URL));
            bean.setSubMenu(parsorSubMenu(object.getJSONArray(BottomNavPoupItemModel.SUB_MENU)));
            bean.setTitle(object.getString(BottomNavPoupItemModel.TAB_TEXT));
            list.add(bean);
        }
        return list;
    }
    private static List<BottomNavPoupItemModel>parsorSubMenu(JSONArray array) throws JSONException {
        List<BottomNavPoupItemModel> list = new ArrayList<>();
        if(array==null||array.length()<1){
            return list;
        }
        for(int i=0;i<array.length();i++){
            JSONObject object = array.getJSONObject(i);
            BottomNavPoupItemModel model = new BottomNavPoupItemModel();
            model.setLabelId(object.getString(BottomNavPoupItemModel.LABEL_ID));
            model.setUrl(object.getString(BottomNavPoupItemModel.URL));
            model.setSubMenu(parsorSubMenu(object.getJSONArray(BottomNavPoupItemModel.SUB_MENU)));
            model.setTitle(object.getString(BottomNavPoupItemModel.TAB_TEXT));
            list.add(model);
        }
        return list;
    }
}
