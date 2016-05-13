package com.viglle.carmanual.modules;

import android.content.Intent;
import android.os.Bundle;

import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.utils.net.TwoValues;
import com.viglle.carmanual.factory.ViewFactory;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public class HomeActivity extends BaseActivity {

//    Map<Integer,BaseViewModel> mTreeMap=new HashMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        Intent intent=getIntent();

        final String url=intent.getStringExtra("url");
        List<TwoValues<String,String>> params = new ArrayList<>();
        params.add(new TwoValues<String, String>("code","home"));
        params.add(new TwoValues<String, String>("andy","home"));
        params.add(new TwoValues<String, String>("boy","home"));

        HttpUtil.httpPost(params, new HttpHandlerInterface() {
            @Override
            public void onSuccess(String data) {

                JSONObject rootObj = null;
                try {
                    rootObj = new JSONObject(data);
                    if (rootObj.getInt("retCode") != 101) {
                        return;
                    }
                    JSONObject rootModelObj = rootObj.getJSONObject("data");
                    BaseViewModel treeModel = VgUIParsor.parserUIModelTree(HomeActivity.this, rootModelObj);
                    setContentView(ViewFactory.createViewTree(HomeActivity.this, treeModel, mViewTreeBean));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, IOException e) {

            }
        });
    }

}
