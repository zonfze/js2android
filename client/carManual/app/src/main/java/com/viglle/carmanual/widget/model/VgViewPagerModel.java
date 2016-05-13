package com.viglle.carmanual.widget.model;

/**
 * Created by Administrator on 2016/5/9.
 */
public class VgViewPagerModel extends BaseViewModel{
    private String noScroll="0";//是否允许ViewPager左右滑动切换 0为默认允许滑动切换;1为不允许滑动切换
    public static final String NOSCROLL="noScroll";

    public boolean noNoScroll() {
        if(noScroll==null||noScroll.equals("")||noScroll.equalsIgnoreCase("null")||noScroll.equals("0")){
            return false;
        }else{
            return true;
        }

    }

    public void setNoScroll(String noScroll) {
        this.noScroll = noScroll;
    }
}
