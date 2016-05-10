package com.viglle.carmanual.modules;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.parsor.VgEventParsor;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.utils.net.TwoValues;
import com.viglle.carmanual.viewfactory.EventFactory;
import com.viglle.carmanual.viewfactory.ViewFactory;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends BaseActivity {



    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<TwoValues<String,String>> params ;
        String url="";
        Intent intent=getIntent();
        if(intent==null){
            params=new ArrayList<>();
        }else{
            url=intent.getStringExtra("url");
            params= (List<TwoValues<String, String>>) intent.getSerializableExtra("params");
        }

        HttpUtil.httpPost(url,params, new HttpHandlerInterface() {
            @Override
            public void onSuccess(String data) {
                JSONObject rootObj = null;
                try {
                    rootObj = new JSONObject(data);
                    if (rootObj.getInt("retCode") != 101) {
                        return;
                    }
                    JSONObject resultObj = rootObj.getJSONObject(DATA);
                    String res_type=resultObj.getString(RES_TYPE);
                    if(res_type.equals(RES_TYPE_1001)){
                        JSONObject uiObj=resultObj.getJSONObject(UI);
                        BaseViewModel treeModel = VgUIParsor.parserModel(mCtx, uiObj);
                        setContentView(ViewFactory.createViewTree(mCtx, treeModel, mViewTreeBean));
                    }else if(res_type.equals(RES_TYPE_1002)){
                        JSONObject actionObj=resultObj.getJSONObject(ACTION_LINK);
                        EventFactory.createActionLink(mCtx, new Button(mCtx), VgEventParsor.parsorActionLink(actionObj));
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
