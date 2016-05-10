package com.viglle.carmanual.widget.layout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.viglle.carmanual.modules.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9.
 */
public class VgViewPager extends ViewPager{
    public VgViewPager(Context context) {

        super(context);
    }

    public VgViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
        return super.onInterceptTouchEvent(ev);
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
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
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
