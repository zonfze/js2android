package com.viglle.carmanual.widget.layout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viglle.carmanual.widget.entity.NavItemModel;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.ImageHelper;

import java.util.ArrayList;
import java.util.List;

public class VgBottomNavLayout<T> extends LinearLayout{
    GradientDrawable gd;
    List<ViewHolder> mList;
    List<NavItemModel> mDatas = new ArrayList<>();
    GradientDrawable pressed =null;
    GradientDrawable normal= null;
    public VgBottomNavLayout(Context context) {
        super(context);
        gd=new GradientDrawable() ;
        gd.setColor(Color.parseColor("#ff0000"));
//        gd.setStroke(1, Color.parseColor("#cccccc"));
        gd.setCornerRadius(AppUtil.dip2px(getContext(), 12));
        normal = new GradientDrawable();
        normal.setColor(Color.parseColor("#000000"));
        pressed = new GradientDrawable();
        pressed.setColor(Color.parseColor("#ff0000"));
        initView();
    }

    public VgBottomNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VgBottomNavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        mList=new ArrayList<>();
        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.dip2px(getContext(), 56)));
        setBackgroundColor();
    }



    private ViewHolder createMenuItem(final int index,NavItemModel<T> model){
        final ViewHolder holder=new ViewHolder();
        holder.relativeLayout=new RelativeLayout(getContext());
        LayoutParams layoutParams=new  LayoutParams(0,AppUtil.dip2px(getContext(),56));
        layoutParams.weight=1;
        holder.relativeLayout.setClickable(true);
        holder.relativeLayout.setLayoutParams(layoutParams);
        holder.relativeLayout.setTag(model);
        holder.tag=model;

        holder.wrap_relativeLayout=new RelativeLayout(getContext());
        RelativeLayout.LayoutParams wrapRelativeParam=new RelativeLayout.LayoutParams(AppUtil.dip2px(getContext(),56),AppUtil.dip2px(getContext(),56));
        wrapRelativeParam.addRule(RelativeLayout.CENTER_IN_PARENT);
        holder.wrap_relativeLayout.setLayoutParams(wrapRelativeParam);
        holder.relativeLayout.addView(holder.wrap_relativeLayout);

        holder.tabicon=new ImageView(getContext());
        RelativeLayout.LayoutParams iconparams=new RelativeLayout.LayoutParams(AppUtil.dip2px(getContext(),56), AppUtil.dip2px(getContext(),56));
        iconparams.addRule(RelativeLayout.CENTER_IN_PARENT);
        holder.tabicon.setLayoutParams(iconparams);
        holder.tabicon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        holder.tabicon.setPadding(AppUtil.dip2px(getContext(), 5), AppUtil.dip2px(getContext(), 5), AppUtil.dip2px(getContext(), 5), AppUtil.dip2px(getContext(), 5));
//        holder.tabicon.setImageResource(R.mipmap.ic_launcher);
        holder.wrap_relativeLayout.addView(holder.tabicon);
        ImageHelper.getInstance().displayImage(getContext(),model.getUnFocusImage(),holder.tabicon);

        holder.redPoint=new TextView(getContext());
        holder.redPoint.setTextSize(8);
        holder.redPoint.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams pp= new RelativeLayout.LayoutParams(AppUtil.dip2px(getContext(),10), AppUtil.dip2px(getContext(),10));
        pp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        pp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        pp.rightMargin=AppUtil.dip2px(getContext(),5);
        pp.topMargin=AppUtil.dip2px(getContext(),5);
        holder.redPoint.setLayoutParams(pp);
        holder.redPoint.setBackgroundDrawable(gd);
        holder.redPointWithNum=new TextView(getContext());
        holder.redPointWithNum.setTextSize(8);
        holder.redPointWithNum.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams pp1= new RelativeLayout.LayoutParams(AppUtil.dip2px(getContext(),28), AppUtil.dip2px(getContext(),28));
        pp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        pp1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        pp1.rightMargin=30;
        pp1.topMargin=30;
        holder.redPointWithNum.setLayoutParams(pp1);
        holder.redPointWithNum.setBackgroundDrawable(gd);
        holder.wrap_relativeLayout.addView(holder.redPoint);
        holder.wrap_relativeLayout.addView(holder.redPointWithNum);
        holder.redPointWithNum.setVisibility(View.GONE);
        holder.redPoint.setVisibility(View.GONE);

        holder.relativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changedFocus(index);
                showRedPoint(index,false);//隐藏红点
                NavItemModel<T> model=(NavItemModel<T>) v.getTag();
                if(mMenuItemClick==null){
                    return;
                }
                mMenuItemClick.onMenuItemClick(holder,model,index);
            }
        });

        return holder;
    }

    public interface OnMenuItemClick<T>{
        /**
         *
         * @param holder 一个Item 中View的持有者;
         * @param model 数据模型
         * @param position 标识的几个菜单(也就是菜单索引)
         */
        void onMenuItemClick(ViewHolder holder,NavItemModel<T> model,int position);
    }

    private OnMenuItemClick mMenuItemClick=null;

    public OnMenuItemClick getmMenuItemClick() {
        return mMenuItemClick;
    }

    public void setmMenuItemClick(OnMenuItemClick mMenuItemClick) {
        this.mMenuItemClick = mMenuItemClick;
    }

    /**
     * 点击item时改变图标样式
     * @param itemIndex
     */
    public void changedFocus(int itemIndex){

        if(itemIndex<0||itemIndex>mList.size()-1){//防止越界
            new Exception("数组越界 itemIndex="+itemIndex).printStackTrace();
            return;
        }
        for(int i=0;i<mList.size();i++){
            if(itemIndex!=i){
                ImageHelper.getInstance().displayImage(getContext(), mDatas.get(i).getUnFocusImage(), mList.get(i).tabicon);
//                mList.get(i).tabicon.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            }else{
                ImageHelper.getInstance().displayImage(getContext(), mDatas.get(i).getFocusImage(), mList.get(i).tabicon);
//                mList.get(i).tabicon.setImageDrawable(pressed);
            }
        }
    }

    public static class ViewHolder{
        public RelativeLayout relativeLayout;
        public RelativeLayout wrap_relativeLayout;//包裹红点和图片icon;这样可以保持图片和红点之间的相对固定距离
        public TextView redPoint;
        public TextView redPointWithNum;

        public ImageView tabicon;
        public TextView tabText;

        public Object tag;

    }

    private void setBackgroundColor(){
        if(Build.VERSION.SDK_INT>=23){
            setBackgroundColor(getResources().getColor(android.R.color.white,null));
        }else{
            setBackgroundColor(getResources().getColor(android.R.color.white));
        }
    }

    public void setDatas(List<NavItemModel> list){
        if(list==null||list.isEmpty()){
            return;
        }
        mDatas.addAll(list);
        for(int i=0;i<list.size();i++){
            ViewHolder holder = createMenuItem(i,list.get(i));
            mList.add(holder);
            addView(holder.relativeLayout);
        }
    }

    /**
     * 设置某个菜单的背景
     * @param resId 资源id
     * @param itemIndex 数组的下标
     */
    public void setMenuItemBackground(int resId,int itemIndex){
        if(itemIndex<0||itemIndex>mList.size()-1){//防止越界
            new Exception("数组越界 itemIndex="+itemIndex).printStackTrace();
            return;
        }

        mList.get(itemIndex).tabicon.setBackgroundResource(resId);
    }
    /**
     * 设置某个菜单的背景
     * @param imageUrl 网络图片url
     * @param itemIndex 数组的下标
     */
    public void setMenuItemBackground(String imageUrl,int itemIndex){
        if(itemIndex<0||itemIndex>mList.size()-1){//防止越界
            new Exception("数组越界 itemIndex="+itemIndex).printStackTrace();
            return;
        }
//        mList.get(itemIndex).tabicon.setBackgroundResource(resId);
    }

    public void setMenuItemImageResource(int resId,int itemIndex){
        if(itemIndex<0||itemIndex>mList.size()-1){//防止越界
            new Exception("数组越界 itemIndex="+itemIndex).printStackTrace();
            return;
        }
        mList.get(itemIndex).tabicon.setImageResource(resId);
    }
    public void setMenuItemImageResource( String imageUrl,int itemIndex){
        if(itemIndex<0||itemIndex>mList.size()-1){//防止越界
            new Exception("数组越界 itemIndex="+itemIndex).printStackTrace();
            return;
        }
//        mList.get(itemIndex).tabicon.setImageResource(resId);
    }

    public void showRedPoint(int itemIndex,boolean isShowed){
        if(itemIndex<0||itemIndex>mList.size()-1){//防止越界
            new Exception("数组越界 itemIndex="+itemIndex).printStackTrace();
            new  Throwable();
            return;
        }
        if(isShowed){
            mList.get(itemIndex).redPoint.setVisibility(View.VISIBLE);
        }else{
            mList.get(itemIndex).redPoint.setVisibility(View.GONE);
        }
    }

}
