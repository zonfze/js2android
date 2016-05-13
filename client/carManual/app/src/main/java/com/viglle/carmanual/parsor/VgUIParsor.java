package com.viglle.carmanual.parsor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.widget.model.VgBottomNavModel;
import com.viglle.carmanual.widget.model.VgBottomNavPupopLayoutModel;
import com.viglle.carmanual.widget.model.VgButtonModel;
import com.viglle.carmanual.widget.model.VgCheckBoxModel;
import com.viglle.carmanual.widget.model.VgContentLayoutModel;
import com.viglle.carmanual.widget.model.VgImageViewModel;
import com.viglle.carmanual.widget.model.VgRadioButtonModel;
import com.viglle.carmanual.widget.model.VgSwitchViewModel;
import com.viglle.carmanual.widget.model.VgTextFieldModel;
import com.viglle.carmanual.widget.model.VgTextViewModel;
import com.viglle.carmanual.widget.model.VgViewPagerModel;
import com.viglle.carmanual.widget.model.VgViewType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/15.
 */
public class VgUIParsor {
    public static final String UI="ui";
    public static BaseViewModel parserUIModelTree(Context context, JSONObject obj) throws JSONException {
        if(obj==null){
            return null;
        }
        LogUtil.log_w(">>>>"+obj.toString());
        JSONObject rootObj = obj.getJSONObject(UI);
        return parserModel(context,rootObj);
    }

    public static BaseViewModel parserModel(Context context, JSONObject rootObj) throws JSONException {

        if(!checkObj(rootObj)) {
            return null;
        }

        int view_type=0;
        try {
            view_type =Integer.parseInt(rootObj.getString(BaseViewModel.VIEW_TYPE));
        }catch (Exception  e){
            e.printStackTrace();
        }
        switch (view_type){
            case VgViewType.VgContentLayout:
                VgContentLayoutModel contentModel=new VgContentLayoutModel();
                parsorCommonParams(rootObj, view_type, contentModel);
                List<BaseViewModel> list = parsorChilds(context, rootObj);
                contentModel.setChilds(list);
                return contentModel;
            case VgViewType.VgTextView:
                VgTextViewModel textViewModel=new VgTextViewModel();
                parsorCommonParams(rootObj, view_type, textViewModel);
                textViewModel.setText(rootObj.getString(VgTextViewModel.TEXT));
                textViewModel.setHint(rootObj.getString(VgTextViewModel.HINT));
                textViewModel.setInputType(rootObj.getString(VgTextViewModel.INPUTTYPE));
//                textViewModel.setPassword(rootObj.getString(VgTextViewModel.PASSWORD));
                textViewModel.setText_align(rootObj.getString(VgTextViewModel.TEXT_ALIGN));
                textViewModel.setText_color(rootObj.getString(VgTextViewModel.TEXT_COLOR));
                textViewModel.setText_size(rootObj.getString(VgTextViewModel.TEXT_SIZE));
                return textViewModel;
            case VgViewType.VgTextField:
                VgTextFieldModel textFieldModel=new VgTextFieldModel();
                parsorCommonParams(rootObj, view_type, textFieldModel);
                textFieldModel.setText(rootObj.getString(VgTextViewModel.TEXT));
                textFieldModel.setHint(rootObj.getString(VgTextViewModel.HINT));
                textFieldModel.setInputType(rootObj.getString(VgTextViewModel.INPUTTYPE));
                textFieldModel.setPassword(rootObj.getString(VgTextFieldModel.PASSWORD));
                textFieldModel.setText_align(rootObj.getString(VgTextFieldModel.TEXT_ALIGN));
                textFieldModel.setText_color(rootObj.getString(VgTextFieldModel.TEXT_COLOR));
                textFieldModel.setText_size(rootObj.getString(VgTextFieldModel.TEXT_SIZE));
                return textFieldModel;
            case VgViewType.VgButton:
                VgButtonModel buttonModel=new VgButtonModel();
                parsorCommonParams(rootObj, view_type, buttonModel);
                buttonModel.setText(rootObj.getString(VgButtonModel.TEXT));
                buttonModel.setText_align(rootObj.getString(VgButtonModel.TEXT_ALIGN));
                buttonModel.setText_color(rootObj.getString(VgButtonModel.TEXT_COLOR));
                buttonModel.setText_size(rootObj.getString(VgButtonModel.TEXT_SIZE));
                return buttonModel;
            case VgViewType.VgImageView:
                VgImageViewModel imageViewModel=new VgImageViewModel();
                parsorCommonParams(rootObj, view_type, imageViewModel);
                imageViewModel.setSrc(rootObj.getString(VgImageViewModel.SRC));
                imageViewModel.setScaleType(rootObj.getString(VgImageViewModel.SCALE_TYPE));
                return imageViewModel;
            case VgViewType.VgCheckBox:
                VgCheckBoxModel checkBoxModel=new VgCheckBoxModel();
                parsorCommonParams(rootObj, view_type, checkBoxModel);
                checkBoxModel.setText(rootObj.getString(VgCheckBoxModel.TEXT));
                checkBoxModel.setIsChecked(rootObj.getString(VgCheckBoxModel.ISCHECKED));
                checkBoxModel.setText_align(rootObj.getString(VgCheckBoxModel.TEXT_ALIGN));
                checkBoxModel.setText_color(rootObj.getString(VgCheckBoxModel.TEXT_COLOR));
                checkBoxModel.setText_size(rootObj.getString(VgCheckBoxModel.TEXT_SIZE));
                return checkBoxModel;
            case VgViewType.VgRadioButton:
                VgRadioButtonModel radioButtonModel=new VgRadioButtonModel();
                parsorCommonParams(rootObj, view_type, radioButtonModel);
                radioButtonModel.setText(rootObj.getString(VgRadioButtonModel.TEXT));
                radioButtonModel.setIsChecked(rootObj.getString(VgCheckBoxModel.ISCHECKED));
                radioButtonModel.setText_align(rootObj.getString(VgRadioButtonModel.TEXT_ALIGN));
                radioButtonModel.setText_color(rootObj.getString(VgRadioButtonModel.TEXT_COLOR));
                radioButtonModel.setText_size(rootObj.getString(VgRadioButtonModel.TEXT_SIZE));
                return radioButtonModel;
            case VgViewType.VgSwitchView:
                VgSwitchViewModel switchViewModel=new VgSwitchViewModel();
                parsorCommonParams(rootObj, view_type, switchViewModel);
                switchViewModel.setIsChecked(rootObj.getString(VgSwitchViewModel.ISCHECKED));
                return switchViewModel;
            case VgViewType.VgListView:

                return null;
            case VgViewType.VgScrollView:

                return null;
            case VgViewType.VgHorizentalScrollView:

                return null;
            case VgViewType.VgLeftNavLayout:

                return null;
            case VgViewType.VgTopNavLayout:

                return null;
            case VgViewType.VgRightNavLayout:

                return null;
            case VgViewType.VgBottomNavLayout:
                VgBottomNavModel bottomNavModel=new VgBottomNavModel();
                parsorCommonParams(rootObj, view_type, bottomNavModel);
                bottomNavModel.setDatas(VgBottomNavModel.parseDatas(rootObj));
                return bottomNavModel;
            case VgViewType.VgBottomNavPupopLayout:
                VgBottomNavPupopLayoutModel navPupopLayoutModel=new VgBottomNavPupopLayoutModel();
                parsorCommonParams(rootObj, view_type, navPupopLayoutModel);
                navPupopLayoutModel.setDatas(VgBottomNavPupopLayoutModel.parseDatas(rootObj));
                return navPupopLayoutModel;
            case VgViewType.VgViewPager:
                VgViewPagerModel viewPagerModel=new VgViewPagerModel();
                viewPagerModel.setNoScroll(rootObj.getString(VgViewPagerModel.NOSCROLL));
                parsorCommonParams(rootObj, view_type, viewPagerModel);
                return viewPagerModel;
            case VgViewType.VgTopActionBar:

                return null;
        }
        return null;
    }

    @NonNull
    private static List<BaseViewModel> parsorChilds(Context context, JSONObject rootObj) {
        List<BaseViewModel> list=new ArrayList<>();
        try{
            JSONArray arr=rootObj.getJSONArray(BaseViewModel.CHILDS);
            for(int i=0;i<arr.length();i++){
                JSONObject obj=arr.getJSONObject(i);
                BaseViewModel mmm=parserModel(context,obj);
                list.add(mmm);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



    /**
     * 解析公共属性函数
     * @param rootObj
     * @param view_type
     * @param contentModel
     * @throws JSONException
     */
    private static void parsorCommonParams(JSONObject rootObj, int view_type, BaseViewModel contentModel) throws JSONException {
        contentModel.setView_id(rootObj.getString(BaseViewModel.VIEW_ID));

        contentModel.setView_type(view_type + "");
        contentModel.setView_name(rootObj.getString(BaseViewModel.VIEW_NAME));
        contentModel.setAction(rootObj.getString(BaseViewModel.ACTION));


        contentModel.setView_height(rootObj.getString(BaseViewModel.VIEW_HEIGHT));
        contentModel.setView_width(rootObj.getString(BaseViewModel.VIEW_WIDTH));

        contentModel.setActionType(rootObj.getString(BaseViewModel.ACTION_TYPE));
        contentModel.setBg_normal_color(rootObj.getString(BaseViewModel.BG_NORMAL_COLOR));
        contentModel.setBg_focus_color(rootObj.getString(BaseViewModel.BG_FOCUS_COLOR));
        contentModel.setKey(rootObj.getString(BaseViewModel.KEY));
        contentModel.setValidLink(VgValidParsor.parsorValidLink(rootObj));


//        JSONObject actionLink=rootObj.getJSONObject(BaseViewModel.ACTION_LINK);
//        LogUtil.log_e("obj",actionLink.toString());
//        if(actionLink!=null){
//            contentModel.setActionLink(VgEventParsor.parsorActionLink(actionLink));
//        }

//        contentModel.setGravity(rootObj.getString(BaseViewModel.GRAVITY));
//        contentModel.setOrientation(rootObj.getString(BaseViewModel.ORIENTATION));


        String[] view_of = parsorViewOf(rootObj);
        contentModel.setView_of(view_of);//相对方位

        String[] margins = parsorMargins(rootObj);
        contentModel.setView_margins(margins);//外边距

        String[] paddings = parsorPaddings(rootObj);
        contentModel.setView_paddings(paddings);//内边距

        List<String> refList = parsorRes(rootObj);
        contentModel.setRefs(refList);//依赖

//        List<String> centersList=parsorCenters(rootObj);
//        contentModel.setCenters(centersList);//居中参数
    }

//    private static List<String> parsorCenters(JSONObject rootObj){
//        List<String> list = new ArrayList<>();
//        try {
//            JSONArray jsonArray=rootObj.getJSONArray(BaseViewModel.CENTERS);
//            for(int i=0;i<jsonArray.length();i++){
//                list.add(jsonArray.getString(i));
//            }
//            return list;
//        }catch (Exception  e) {
//            e.printStackTrace();
//        }
//        return list;
//    };

    private static String[] parsorViewOf(JSONObject rootObj){
        String[] view_of=new String[]{"-1","-1","-1","-1"};
        try {
            JSONArray view_of_array=rootObj.getJSONArray(BaseViewModel.VIEW_OF);
            for(int i=0;i<view_of_array.length();i++){
                view_of[i]=view_of_array.getString(i);
            }
            return view_of;
        }catch (Exception  e) {
            e.printStackTrace();
        }
        return view_of;
    }

    @NonNull
    private static List<String> parsorRes(JSONObject rootObj) {
        final List<String> refList=new ArrayList<>();
        try {
            JSONArray array=rootObj.getJSONArray(VgButtonModel.REFS);
            for(int i=0;i<array.length();i++){
                String view_id=array.getString(i);
                refList.add(view_id);
            }
            return refList;
        }catch (Exception  e) {
            e.printStackTrace();
        }
       return refList;
    }

    private static String[] parsorMargins(JSONObject rootObj){
        String margins[]=new String[]{"0","0","0","0"};
        try{
            JSONArray view_margins=rootObj.getJSONArray(VgButtonModel.VIEW_MARGINS);
            for(int i=0;i<view_margins.length();i++){
                margins[i]=view_margins.getString(i);
            }
            return margins;
        }catch (Exception e){
            e.printStackTrace();
        }

        return margins;
    }

    private static String[] parsorPaddings(JSONObject rootObj){
        String paddings[]=new String[]{"0","0","0","0"};
        try{
            JSONArray view_paddings=rootObj.getJSONArray(VgButtonModel.VIEW_PADDINGS);
            for(int i=0;i<view_paddings.length();i++){
                paddings[i]=view_paddings.getString(i);
            }
            return paddings;
        }catch (Exception e){
            e.printStackTrace();
        }

        return paddings;
    }

    private static boolean checkObj(JSONObject obj) {
        if(obj==null){//参数合法校验
            return false;
        }
        String objStr=obj.toString();
        if(objStr.equals("{}")||objStr.equals("")||objStr.equals("null")){//参数合法校验
            return false;
        }
        return true;
    }
}
