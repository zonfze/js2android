package com.viglle.carmanual.widget.layout;

/**
 * Created by Administrator on 2016/5/9.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.modules.user.LoginActivity;
import com.viglle.carmanual.modules.fragments.BaseFragment;
import com.viglle.carmanual.modules.fragments.CustomFragment;
import com.viglle.carmanual.seletor.SelectorFactory;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.ToastUtil;
import com.viglle.carmanual.utils.net.TwoValues;
import com.viglle.carmanual.widget.entity.BottomNavPoupItemModel;
import com.viglle.carmanual.widget.entity.ViewTreeBean;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.widget.model.RefsBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/5/5.
 */
public class VgBottomNavPupopLayout extends LinearLayout {
    public List<BottomNavPoupItemModel> mDatas=new ArrayList<>();
    public List<ViewHolder> mList=new ArrayList<>();
    LinearLayout mMenu=new LinearLayout(getContext());
    View mLine=new View(getContext());
    Drawable drawable =null;

    FragmentManager mFrgManager=null;

    public VgBottomNavPupopLayout(Context context) {
        super(context);
        initView();
    }

    public VgBottomNavPupopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VgBottomNavPupopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        mFrgManager = ((BaseActivity) getContext()).getSupportFragmentManager();
        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.dip2px(getContext(), 48)));
        setBackgroundColor(Color.parseColor("#88cccccc"));
        drawable=new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                Paint pant=new Paint();
                pant.setStyle(Paint.Style.FILL);
                pant.setColor(Color.parseColor("#ffe6e6e6"));
                Path path=new Path();
                path.moveTo(AppUtil.dip2px(getContext(),15),0);
                path.lineTo(AppUtil.dip2px(getContext(),15), AppUtil.dip2px(getContext(),15));
                path.lineTo(0, AppUtil.dip2px(getContext(),15));
                path.close();
                canvas.drawPath(path,pant);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };
        Button btn=new Button(getContext());
        LinearLayout.LayoutParams btnParams=new LayoutParams(AppUtil.dip2px(getContext(),48),AppUtil.dip2px(getContext(),48));
        btnParams.setMargins(0, 1, 1, 1);
        btn.setText("qq");
        btn.setBackgroundColor(Color.parseColor("#ffffffff"));
        btn.setGravity(Gravity.CENTER);
        btn.setLayoutParams(btnParams);

    }

    public static class ViewHolder{

    }

    public List<BottomNavPoupItemModel> getDatas(){
        List<BottomNavPoupItemModel> list = new ArrayList<>();
        BottomNavPoupItemModel model=new BottomNavPoupItemModel();
        model.setLabelId("1");
        model.setTitle("常用资料");
        model.setUrl("http://192.168.16.198:8082/?code=welcome");
        list.add(model);

        model=new BottomNavPoupItemModel();
        model.setLabelId("2");
        model.setTitle("汽修人");
        list.add(model);

        List<BottomNavPoupItemModel> subItemList=new ArrayList<>();
        BottomNavPoupItemModel itemModel=new BottomNavPoupItemModel();
        itemModel.setLabelId("101");
        itemModel.setTitle("修车问答");
        subItemList.add(itemModel);//添加子菜单

        itemModel=new BottomNavPoupItemModel();
        itemModel.setLabelId("102");
        itemModel.setTitle("修车生活");
        subItemList.add(itemModel);//添加子菜单

        itemModel=new BottomNavPoupItemModel();
        itemModel.setLabelId("103");
        itemModel.setTitle("修车案例");
        subItemList.add(itemModel);//添加子菜单

        itemModel=new BottomNavPoupItemModel();
        itemModel.setLabelId("104");
        itemModel.setTitle("修车趣事");
        subItemList.add(itemModel);//添加子菜单

        itemModel=new BottomNavPoupItemModel();
        itemModel.setLabelId("105");
        itemModel.setTitle("修车趣事");
        subItemList.add(itemModel);//添加子菜单

        model.setSubMenu(subItemList);//将子菜单添加到一级菜单中


        model=new BottomNavPoupItemModel();
        model.setLabelId("3");
        model.setTitle("我");
        List<BottomNavPoupItemModel> subItemList1=new ArrayList<>();
        BottomNavPoupItemModel itemModel1=new BottomNavPoupItemModel();
        itemModel1.setLabelId("201");
        itemModel1.setTitle("注册");
        subItemList1.add(itemModel1);//添加子菜单

        itemModel1=new BottomNavPoupItemModel();
        itemModel1.setLabelId("202");
        itemModel1.setTitle("登录");
        subItemList1.add(itemModel1);//添加子菜单

        model.setSubMenu(subItemList1);
        list.add(model);
        return list;
    }

    public void setDatas(List<BottomNavPoupItemModel> list){
        if(list==null||list.isEmpty()){
            return;
        }
        if(!mDatas.isEmpty()){
            mDatas.clear();
        }
        mDatas.addAll(list);
        List<android.support.v4.app.Fragment> fragmentList=new ArrayList<>();
        for (int i=0;i<mDatas.size();i++){
            BottomNavPoupItemModel model = mDatas.get(i);
            addView(createItem(model, i));
            BaseFragment fragment=new CustomFragment();
            fragment.setUrl(model.getUrl());
            fragment.setmTag(model.getLabelId());
            fragmentList.add(fragment);
        }

        VgViewPager viewPager= getVgViewPager();
        if(viewPager==null){
            return;
        }
        viewPager.setDatas(((BaseActivity) getContext()).getSupportFragmentManager(),fragmentList);
        setCurrentItem(0);
    }

    private VgViewPager getVgViewPager(){
        ViewTreeBean map = ((BaseActivity) getContext()).getmViewTree();
        LogUtil.log_e("bottom_nav_id==" + getId());
        BaseViewModel model = map.getViewModelById(getId());
        if(model==null){
            return null;
        }
        RefsBean refsBean= model .getRefs();
        if(refsBean==null){
            return null;
        }
        List<Integer> ids=refsBean.getRefsId();
        if(ids==null||ids.isEmpty()){
            return null;
        }
        Integer viewId=ids.get(0);
        VgViewPager viewPager= (VgViewPager) map.getViewById(viewId);
        return viewPager;
    }

    private View createItem(final BottomNavPoupItemModel model,final int p) {
        final RelativeLayout itemLayout=new RelativeLayout(getContext());
        itemLayout.setClickable(true);

        LayoutParams itemLayoutParams=new LayoutParams(0, AppUtil.dip2px(getContext(),48));
        itemLayoutParams.weight=1;
        if(p==mDatas.size()-1){
            itemLayoutParams.setMargins(0, 1, 0, 0);
        }else{
            itemLayoutParams.setMargins(0, 1, 1, 0);
        }

        itemLayout.setLayoutParams(itemLayoutParams);
        itemLayout.setBackgroundColor(Color.parseColor("#ffffffff"));

        if(model.getSubMenu()!=null&&!model.getSubMenu().isEmpty()){
            ImageView mAngle=new ImageView(getContext());
            RelativeLayout.LayoutParams imageParams=new RelativeLayout.LayoutParams(AppUtil.dip2px(getContext(),15),
                    AppUtil.dip2px(getContext(),15));
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            imageParams.setMargins(0, 0, AppUtil.dip2px(getContext(),5), AppUtil.dip2px(getContext(),5));
            mAngle.setLayoutParams(imageParams);
            mAngle.setBackgroundDrawable(drawable);
            itemLayout.addView(mAngle);
        }

        TextView textView = new TextView(getContext());
        RelativeLayout.LayoutParams txtParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        setItemBackgroud(itemLayout);
        textView.setText(model.getTitle());
        textView.setTextSize(AppUtil.px2sp(getContext(),42));
//            textView.setBackgroundColor(Color.parseColor("#ffffffff"));
        textView.setTextColor(Color.parseColor("#ff000000"));
        textView.setGravity(Gravity.CENTER);
        itemLayout.addView(textView);
        textView.setLayoutParams(txtParams);
        itemLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getSubMenu() != null && !model.getSubMenu().isEmpty()) {
                    showPoupWindow(itemLayout, model);
                } else {
                    setCurrentItem(p);
                    ToastUtil.showToast(getContext(), "无子菜单");
                }
            }
        });


        return itemLayout;
    }

    private  void setCurrentItem(int index){

        VgViewPager vgViewPager=getVgViewPager();
        if(vgViewPager==null){
            return;
        }

        PagerAdapter adapter=vgViewPager.getAdapter();
        if (adapter==null){
            return;
        }
        if(index<0||index>adapter.getCount()){//避免数组越界
            return;
        }
        vgViewPager.changedCurrentItem(index);
    }
    private void showPoupWindow(View clickedView, final BottomNavPoupItemModel model){

        if(model.getSubMenu()==null||model.getSubMenu().isEmpty()){
            return;
        }
        int width=clickedView.getWidth();
        ListView listView = new ListView(getContext());
        listView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        listView.setSelector(SelectorFactory.getInstance().newSeletor(getContext(),
                "#ffffffff",
                "#ffe6e6e6",
                "#ffe6e6e6",
                "#ffffffff"));
        MySubMenuAdapter adapter=new MySubMenuAdapter(getContext());
        adapter.setSubDatas(model.getSubMenu());
        listView.setAdapter(adapter);
        listView.setVerticalScrollBarEnabled(false);

        final PopupWindow popupWindow=new PopupWindow();
        popupWindow.setContentView(listView);
        popupWindow.setWidth(width);
        popupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                BottomNavPoupItemModel itemModel = (BottomNavPoupItemModel) parent.getItemAtPosition(position);
                ToastUtil.showToast(getContext(), itemModel.getTitle());
                Intent intent=new Intent(getContext(),LoginActivity.class);
                intent.putExtra("url",itemModel.getUrl());
                intent.putExtra("params", new ArrayList<TwoValues<String,String>>());
                getContext().startActivity(intent);

            }
        });
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int [] whArray=new int[2];
        clickedView.getLocationOnScreen(whArray);
        float dd=whArray[1]-model.getSubMenu().size()*(AppUtil.dip2px(getContext(),48)+AppUtil.getDensity(getContext()));
        popupWindow.showAtLocation(clickedView,0,whArray[0],(int)dd);
    }

//    Map<String ,BaseFragment> frgStack=new HashMap<String,BaseFragment>();
//
//    public BaseFragment findFrgByTag(String tag){
//        if(!frgStack.containsKey(tag)){
//           return null;
//        }
//        return frgStack.get(tag);
//    }

//    public void showFrg(BottomNavPoupItemModel model,int resid){
//        BaseFragment fragment=findFrgByTag(model.getLabelId());
//        FragmentTransaction transaction=mFrgManager.beginTransaction();
//        if(fragment==null){
//            fragment=new CustomFragment();
//            fragment.setUrl(model.getUrl());
//            fragment.setmTag(model.getLabelId());
//
//            transaction.add(resid,fragment).commit();
//            frgStack.put(model.getLabelId(),fragment);
//        }else{
//            transaction.show(fragment).commit();
//        }
//    }


    private void setItemBackgroud(View view){
        if(Build.VERSION.SDK_INT>=16){
            view.setBackground(SelectorFactory.getInstance().newSeletor(
                    getContext(),
                    "#ffffffff",
                    "#ffe6e6e6",
                    "#ffe6e6e6",
                    "#ffffffff"));
        }else{
            view.setBackgroundDrawable(SelectorFactory.getInstance().newSeletor(getContext(),
                    "#ffffffff",
                    "#ffe6e6e6",
                    "#ffe6e6e6",
                    "#ffffffff"));
        }
    }

    private class MySubMenuAdapter extends BaseAdapter {
        Context mCtx;

        private List<BottomNavPoupItemModel> mList = new ArrayList<>();
        public MySubMenuAdapter(Context ctx) {
            mCtx=ctx;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public BottomNavPoupItemModel getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(mCtx);

            textView.setText(getItem(position).getTitle());
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,AppUtil.dip2px(mCtx,48)));
            textView.setTextColor(Color.parseColor("#ff000000"));
//            textView.setPadding(AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10));
            setItemBackgroud(textView);
            return textView;
        }

        public void setSubDatas(List<BottomNavPoupItemModel> list){
            if(list==null){
                return;
            }
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

//    public void notifyDataSetChanged(){
//        removeAllViews();
//        for(){}
//    }

}
