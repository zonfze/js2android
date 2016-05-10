package com.viglle.carmanual.widget.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.FragmentManager;
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
import com.viglle.carmanual.parsor.VgEventParsor;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.seletor.SelectorFactory;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.ToastUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.viewfactory.EventFactory;
import com.viglle.carmanual.viewfactory.ViewFactory;
import com.viglle.carmanual.widget.layout.VgContentLayout;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.widget.model.RefsBean;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/5.
 */
public class VgBottomNavPupopLayout extends LinearLayout{
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
        LayoutParams btnParams=new LayoutParams(AppUtil.dip2px(getContext(),48),AppUtil.dip2px(getContext(),48));
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

        List<BottomNavPoupItemModel.ItemModel> subItemList=new ArrayList<>();
        BottomNavPoupItemModel.ItemModel itemModel=new BottomNavPoupItemModel.ItemModel();
        itemModel.setSubLabelId("101");
        itemModel.setSubtitle("修车问答");
        subItemList.add(itemModel);//添加子菜单

        itemModel=new BottomNavPoupItemModel.ItemModel();
        itemModel.setSubLabelId("102");
        itemModel.setSubtitle("修车生活");
        subItemList.add(itemModel);//添加子菜单

        itemModel=new BottomNavPoupItemModel.ItemModel();
        itemModel.setSubLabelId("103");
        itemModel.setSubtitle("修车案例");
        subItemList.add(itemModel);//添加子菜单

        itemModel=new BottomNavPoupItemModel.ItemModel();
        itemModel.setSubLabelId("104");
        itemModel.setSubtitle("修车趣事");
        subItemList.add(itemModel);//添加子菜单

        itemModel=new BottomNavPoupItemModel.ItemModel();
        itemModel.setSubLabelId("105");
        itemModel.setSubtitle("修车趣事");
        subItemList.add(itemModel);//添加子菜单

        model.setSubMenu(subItemList);//将子菜单添加到一级菜单中


        model=new BottomNavPoupItemModel();
        model.setLabelId("3");
        model.setTitle("我");
        List<BottomNavPoupItemModel.ItemModel> subItemList1=new ArrayList<>();
        BottomNavPoupItemModel.ItemModel itemModel1=new BottomNavPoupItemModel.ItemModel();
        itemModel1.setSubLabelId("201");
        itemModel1.setSubtitle("注册");
        subItemList1.add(itemModel1);//添加子菜单

        itemModel1=new BottomNavPoupItemModel.ItemModel();
        itemModel1.setSubLabelId("202");
        itemModel1.setSubtitle("登录");
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
//        List<android.support.v4.app.Fragment> fragmentList=new ArrayList<>();
        for (int i=0;i<mDatas.size();i++){
            BottomNavPoupItemModel model = mDatas.get(i);
            addView(createItem(model, i));
        }
       final BottomNavPoupItemModel model=mDatas.get(0);
        setCurrentItem(model.getLabelId(),model.getUrl());
    }

    private VgContentLayout getContentView(){
        ViewTreeBean map = ((BaseActivity) getContext()).getmViewTree();
        LogUtil.log_e("bottom_nav_id=="+getId());
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
        VgContentLayout contentLayout= (VgContentLayout) map.getViewById(viewId);
        return contentLayout;
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
                    setCurrentItem(model.getLabelId(),model.getUrl());
                    ToastUtil.showToast(getContext(), "无子菜单");
                }
            }
        });


        return itemLayout;
    }

    ViewPanelManger manger=new ViewPanelManger();

    private  void setCurrentItem(final String labelId,final String url){

       final VgContentLayout vgViewPager=getContentView();
        if(vgViewPager==null){
            return;
        }
        View view=manger.getViewByKey(labelId);

        if(view==null){
            HttpUtil.httpGet(url, new HttpHandlerInterface() {
                @Override
                public void onSuccess(String data) {
                    JSONObject rootObj = null;
                    try {
                        rootObj = new JSONObject(data);
                        if (rootObj.getInt("retCode") != 101) {
                            return;
                        }
                        JSONObject resultObj = rootObj.getJSONObject(BaseActivity.DATA);
                        String res_type=resultObj.getString(BaseActivity.RES_TYPE);
                        if(res_type.equals(BaseActivity.RES_TYPE_1001)){
                            JSONObject uiObj=resultObj.getJSONObject(BaseActivity.UI);
                            BaseViewModel treeModel = VgUIParsor.parserModel(getContext(), uiObj);
                            View panel = ViewFactory.createViewTree(getContext(), treeModel, ((BaseActivity)getContext()).getmViewTree());
                            manger.put(labelId,panel);
                            vgViewPager.removeAllViews();
                            vgViewPager.addView(panel);
                        }else if(res_type.equals(BaseActivity.RES_TYPE_1002)){
                            JSONObject actionObj=resultObj.getJSONObject(BaseActivity.ACTION_LINK);
                            EventFactory.createActionLink(getContext(), new Button(getContext()), VgEventParsor.parsorActionLink(actionObj));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            vgViewPager.removeAllViews();

            vgViewPager.addView(view);
        }

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
                BottomNavPoupItemModel.ItemModel itemModel = (BottomNavPoupItemModel.ItemModel) parent.getItemAtPosition(position);
                ToastUtil.showToast(getContext(), itemModel.getSubtitle());
                popupWindow.dismiss();
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

    private class MySubMenuAdapter extends BaseAdapter{
        Context mCtx;

        private List<BottomNavPoupItemModel.ItemModel> mList = new ArrayList<>();
        public MySubMenuAdapter(Context ctx) {
            mCtx=ctx;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public BottomNavPoupItemModel.ItemModel getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(mCtx);

            textView.setText(getItem(position).getSubtitle());
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,AppUtil.dip2px(mCtx,48)));
            textView.setTextColor(Color.parseColor("#ff000000"));
//            textView.setPadding(AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10));
            setItemBackgroud(textView);
            return textView;
        }

        public void setSubDatas(List<BottomNavPoupItemModel.ItemModel> list){
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

    public static class ViewPanelManger{
        protected Map<String,View> panelMap=new HashMap<>();

        public void put(String labelId,View view){
            panelMap.put(labelId,view);
        }

        public View getViewByKey(String labelId){
            if(!panelMap.containsKey(labelId)){
                return null;
            }
            return panelMap.get(labelId);
        }

        public void updateView(String labelId,View view){
            if(!panelMap.containsKey(labelId)){
                panelMap.put(labelId,view);
                return;
            }
            panelMap.remove(labelId);
            panelMap.put(labelId,view);
        }
    }
}
