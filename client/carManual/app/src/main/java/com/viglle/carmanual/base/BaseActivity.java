package com.viglle.carmanual.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.viglle.carmanual.utils.ToastUtil;
import com.viglle.carmanual.widget.entity.ViewTreeBean;

/**
 * Created by Administrator on 2016/5/4.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public int mUiType=100;//记录当前界面所属的类型(@link{UIType.java}),默认是普通类型的界面
    protected ViewTreeBean mViewTreeBean=new ViewTreeBean();
    public void clearMap(){
        mViewTreeBean.clear();
    }

    public ViewTreeBean getmViewTree(){
        return mViewTreeBean;
    }
    public static final String DATA="data";
    public static final String RES_TYPE="res_type";


    public static final String ACTION_LINK="actionLink";
    public static final String MSG="msg";
    public static final String RES_TYPE_1001="1001";//ui界面数据
    public static final String RES_TYPE_1002="1002";//action数据

    protected Context mCtx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx=this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mUiType!=UIType.UI_MAIN){
            return super.onKeyDown(keyCode, event);
        }else{
            showExitApp();
            return true;
        }
    }
    long exitTime = 0;

    /**
     * 展示退出程序
     */
    private void showExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast(this,"再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }
}
