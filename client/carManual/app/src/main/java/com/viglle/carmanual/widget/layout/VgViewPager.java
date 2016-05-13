package com.viglle.carmanual.widget.layout;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.List;

/**
 * Created by Administrator on 2016/5/9.
 */
public class VgViewPager extends ViewPager{
    private  boolean noScroll=false;
    public VgViewPager(Context context) {
        super(context);
        if(Build.VERSION.SDK_INT>=11){
//            setPageTransformer(true,new DepthPageTransformer());//添加ViewPager切换动画
        }
    }

    public VgViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {

        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    public void setDatas(FragmentManager fm,List<Fragment> list){////
        if(list==null||list.isEmpty()){
            return;
        }
        VgViewPagerAdapter adapter = new VgViewPagerAdapter(fm,list);
        this.setAdapter(adapter);
    }

    public void changedCurrentItem(int index){
       int count= getAdapter().getCount();
        if(index>count||index<0){
            return;
        }
        VgViewPager.this.setCurrentItem(index);
    }

    public static class VgViewPagerAdapter extends FragmentPagerAdapter{
        public List<Fragment> mList=null;
        public VgViewPagerAdapter(FragmentManager fm,List<Fragment>list) {
            super(fm);
            mList=list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList==null?0:mList.size();
        }
    }
}
