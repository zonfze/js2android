package com.viglle.carmanual.modules.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.viglle.carmanual.action.model.BaseEventModel;
import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.base.UIType;
import com.viglle.carmanual.factory.EventFactory;
import com.viglle.carmanual.factory.ViewFactory;
import com.viglle.carmanual.parsor.VgEventParsor;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.utils.net.TwoValues;
import com.viglle.carmanual.widget.model.BaseViewModel;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends BaseActivity {

    String url="";
//    private String cacheKey="";

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<TwoValues<String,String>> params ;
        mUiType= UIType.UI_LOGIN;
        Intent intent=getIntent();
        if(intent==null){
            params=new ArrayList<>();
        }else{
            url=intent.getStringExtra("url");
            params= (List<TwoValues<String, String>>) intent.getSerializableExtra("params");
        }
      //  cacheKey= AppUtil.buildCacheKey(url,params);
//        String jsonStr = SharedPrefUtil.getInstance(this).getProperty(cacheKey,"");
//        if(!jsonStr.equals("")){
//            LogUtil.log_e("local_json",jsonStr);
//            handlerResult(jsonStr);
//            return;
//        }

        HttpUtil.httpPost(url,params, new HttpHandlerInterface() {
            @Override
            public void onSuccess(String data) {
                handlerResult(data);
            }

            @Override
            public void onFailure(int statusCode, IOException e) {

                LogUtil.log_e(e.toString());
            }
        });
    }

    private void handlerResult(String jsonStr){
        JSONObject rootObj = null;
        try {
            rootObj = new JSONObject(jsonStr);
            if (rootObj.getInt("retCode") != 101) {
                return;
            }
            JSONObject resultObj = rootObj.getJSONObject(DATA);
            String res_type=resultObj.getString(RES_TYPE);
            if(res_type.equals(RES_TYPE_1001)){
                BaseViewModel treeModel = VgUIParsor.parserUIModelTree(mCtx, resultObj);
                View view=ViewFactory.createViewTree(mCtx, treeModel, mViewTreeBean);

                List<BaseEventModel> eventModelList=VgEventParsor.parsorEventLink(resultObj);
                EventFactory.createEventLink(mCtx, mViewTreeBean, eventModelList);
                if(view!=null){
                    setContentView(view);
                }
            }else if(res_type.equals(RES_TYPE_1002)){
                EventFactory.createEventLink(mCtx, mViewTreeBean, VgEventParsor.parsorEventLink(resultObj));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
