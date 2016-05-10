package com.viglle.carmanual.modules.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.viglle.carmanual.utils.net.TwoValues;
import com.viglle.carmanual.widget.entity.ViewTreeBean;
import com.viglle.carmanual.widget.model.BaseViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class BaseFragment extends Fragment {
    protected LinearLayout mRootView;
    protected ViewTreeBean viewTreeBean=new ViewTreeBean();
//    protected Context mCtx;
    public void clearMap(){
        viewTreeBean.clear();
    }
//    public void setViewMap(Map<Integer,BaseViewModel> map){
//        if(map==null){
//            return;
//        }
//        if(mViewMap!=null&&!mViewMap.isEmpty()){
//            mViewMap.clear();
//        }
//        mViewMap.putAll(map);
//    }

    public String mUrl="";
    public String mTag="";
    public List<TwoValues<String,String>> params=new ArrayList<>();

    public void setUrl(String url){
        mUrl=url;
    }
    public void setmTag(String tag){
        mTag=tag;
    }

    public void setParams(List<TwoValues<String,String>> list){
        if(list==null||list.isEmpty()){
            return;
        }
        params.clear();
        params.addAll(list);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mCtx=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
