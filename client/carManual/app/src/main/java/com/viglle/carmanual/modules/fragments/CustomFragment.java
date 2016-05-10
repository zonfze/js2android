package com.viglle.carmanual.modules.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.parsor.VgEventParsor;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.viewfactory.EventFactory;
import com.viglle.carmanual.viewfactory.ViewFactory;
import com.viglle.carmanual.widget.model.BaseViewModel;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2016/5/6.
 */
public class CustomFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = new LinearLayout(getActivity());
        mRootView.setBackgroundColor(Color.parseColor("#ff00ff00"));
        mRootView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LogUtil.log_e("mUrl="+mUrl);
        LogUtil.log_e("mTag="+mTag);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mUrl==null||mUrl.equals("")||mUrl.equals("null")){
            return;
        }
        HttpUtil.httpGet(mUrl, new HttpHandlerInterface() {
            @Override
            public void onSuccess(String data) {
                JSONObject rootObj = null;
                try {
                    rootObj = new JSONObject(data);
                    if (rootObj.getInt("retCode") != 101) {
                        return;
                    }
                    JSONObject resultObj = rootObj.getJSONObject(BaseActivity.DATA);
                    String res_type = resultObj.getString(BaseActivity.RES_TYPE);
                    if (res_type.equals(BaseActivity.RES_TYPE_1001)) {
                        JSONObject uiObj = resultObj.getJSONObject(BaseActivity.UI);
                        BaseViewModel treeModel = VgUIParsor.parserModel(getActivity(), uiObj);
                        mRootView.addView(ViewFactory.createViewTree(getActivity(), treeModel, viewTreeBean));
                    } else if (res_type.equals(BaseActivity.RES_TYPE_1002)) {
                        JSONObject actionObj = resultObj.getJSONObject(BaseActivity.ACTION_LINK);
                        EventFactory.createActionLink(getActivity(), new Button(getActivity()), VgEventParsor.parsorActionLink(actionObj));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, IOException e) {

                LogUtil.log_e(e.toString());
            }
        });

    }
}
