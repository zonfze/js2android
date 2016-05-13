package com.viglle.carmanual.widget.entity;

import android.view.View;

import com.viglle.carmanual.widget.model.BaseViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/9.
 */
public class ViewTreeBean {
    protected Map<Integer,BaseViewModel> mViewMap=new HashMap<>();
    public void put(BaseViewModel model){
        if(model==null){
            return;
        }
        mViewMap.put(model.getView_id(),model);
    }

    public void put(Integer viewId,BaseViewModel model){
        if(model==null){
            return;
        }
        mViewMap.put(viewId,model);
    }

    public BaseViewModel getViewModelById(Integer viewId){
        if(!mViewMap.containsKey(viewId)){
            return null;
        }
        return mViewMap.get(viewId);
    }

    /**
     * 通过泛型方法获取model
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends BaseViewModel> T getCastViewModelById(Integer viewId){
        return (T)getViewModelById(viewId);
    }

    public View getViewById(Integer viewId){
        if(!mViewMap.containsKey(viewId)){
            return null;
        }
        return mViewMap.get(viewId).getVgView();
    }

    public void clear(){
        mViewMap.clear();
    }
}
