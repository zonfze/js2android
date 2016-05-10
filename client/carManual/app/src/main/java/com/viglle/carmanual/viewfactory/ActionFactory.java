package com.viglle.carmanual.viewfactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.viglle.carmanual.action.ActionType;
import com.viglle.carmanual.action.model.ActionCloseNewModel;
import com.viglle.carmanual.action.model.ActionHttpModel;
import com.viglle.carmanual.action.model.ActionToastModel;
import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.widget.entity.ViewTreeBean;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.widget.model.VgTextFieldModel;
import com.viglle.carmanual.widget.model.VgViewType;
import com.viglle.carmanual.modules.LoginActivity;
import com.viglle.carmanual.parsor.VgEventParsor;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.ToastUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.utils.net.TwoValues;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/4.
 */
public class ActionFactory {
    public static void createActionLink(Context ctx,View view,List<BaseActionModel> links){
        if(links==null||links.isEmpty()){//校验参数是否异常,异常则返回
            return;
        }
        for(int i=0;i<links.size();i++){
            BaseActionModel model=links.get(i);
            createAction(ctx,view,model);
        }
    }

    public static void createAction(final Context ctx,View view,BaseActionModel model){
        if(model==null){//检验参数是否异常
            return;
        }
        int actionType=model.getActionType();
        switch (actionType){
            case ActionType.ACTION_SHOW_TOAST://显示Toast
                ActionToastModel modelToast= (ActionToastModel) model;
                ToastUtil.showToast(ctx,modelToast.getText());
                break;
            case ActionType.ACTION_SHOW_SNACKBAR://显示Snackbar

                break;
            case ActionType.ACTION_OBTAIN_PARAM_FROM_SDCARD://从sdcard读取数据

                break;
            case ActionType.ACTION_SAVE_PARAM_TO_SDCARD://将数据保存到sdcard

                break;
            case ActionType.ACTION_OBTAIN_PARAM_FROM_VIEW://从获取控件的值;比如EditText中的文字getText()

                break;
            case ActionType.ACTION_UPDATE_VIEW://刷新UI

                break;
            case ActionType.ACTION_NEW_PANEL://打开一个新界面

                break;
            case ActionType.ACTION_CLOSE_PANEL://关闭一个界面

                break;
            case ActionType.ACTION_CLOSE_AND_NEW_PANEL://打开一个新界面并关闭上一个界面
                ActionCloseNewModel closeNewModel= (ActionCloseNewModel) model;
                Intent intent=new Intent(ctx,LoginActivity.class);
                intent.putExtra("params", (Serializable) model.getParams());
                intent.putExtra("url", closeNewModel.getUrl());
                ctx.startActivity(intent);
                ((Activity)ctx).finish();
                break;
            case ActionType.ACTION_SEND_HTTP_REQUEST://发送httpq请求
                ActionHttpModel modelHttp= (ActionHttpModel) model;
                ViewTreeBean allViewMap =((BaseActivity)ctx).getmViewTree();
                List<TwoValues<String,String>> list = configRefUIParams((BaseActivity) ctx, modelHttp, allViewMap);//获取控件上的值组装成请求参数的格式
                list.addAll(modelHttp.getParams());//合并参数

                HttpUtil.httpPost(modelHttp.getUrl(),list , new HttpHandlerInterface() {
                    @Override
                    public void onSuccess(String data) {
                        JSONObject rootObj = null;
                        try {
                            rootObj = new JSONObject(data);
                            if (rootObj.getInt("retCode") != 101) {
                                ToastUtil.showToast(ctx,rootObj.getString(BaseActivity.MSG));
                                return;
                            }
                            JSONObject resultObj = rootObj.getJSONObject(BaseActivity.DATA);
                            String res_type=resultObj.getString(BaseActivity.RES_TYPE);
                            if(res_type.equals(BaseActivity.RES_TYPE_1001)){
                                JSONObject uiObj=resultObj.getJSONObject(BaseActivity.UI);
                                BaseViewModel treeModel = VgUIParsor.parserModel(ctx, uiObj);
                                ((BaseActivity)ctx).setContentView(ViewFactory.createViewTree(ctx,treeModel,((BaseActivity)ctx).getmViewTree()));
                            }else if(res_type.equals(BaseActivity.RES_TYPE_1002)){
                                JSONObject actionObj=resultObj.getJSONObject(BaseActivity.ACTION_LINK);
                                EventFactory.createActionLink(ctx, new Button(ctx), VgEventParsor.parsorActionLink(actionObj));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, IOException e) {
                        e.printStackTrace();
                        ToastUtil.showToast(ctx,"网络不给力啊");
                    }
                });
                break;
            case ActionType.ACTION_COUNT_TIMER://计时

                break;
            case ActionType.ACTION_COUNT_DOWN_TIMER://倒计时

                break;
            case ActionType.ACTION_SHOW_DIALOG://显示对话框

                break;
            case ActionType.ACTION_DIMISS_DIALOG://隐藏对话框

                break;

        }
    }

    private static List<TwoValues<String,String>> configRefUIParams(BaseActivity ctx, ActionHttpModel modelHttp, ViewTreeBean allViewMap) {
        List<Integer> refUI=modelHttp.getRef_ui();
        List<TwoValues<String,String>> list=new ArrayList<>();
        for(int i=0;i<refUI.size();i++){
            int view_id=refUI.get(i);
            BaseViewModel baseViewModel = allViewMap.getViewModelById(view_id);
            int view_type=baseViewModel.getView_type();
            switch (view_type){
                case VgViewType.VgTextField:
                    VgTextFieldModel textFieldModel= (VgTextFieldModel)allViewMap.getViewModelById(view_id);
                    String key=textFieldModel.getKey();
                    EditText editText=(EditText)textFieldModel.getVgView();
                    String value=editText.getText().toString();
                    list.add(new TwoValues<String,String>(key,value));
                    break;
                case VgViewType.VgRadioButton:
                    break;
                case VgViewType.VgTextView:

                    break;
            }
        }
        return list;
    }
}
