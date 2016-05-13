package com.viglle.carmanual.widget.model;

import android.view.View;

import com.viglle.carmanual.action.model.BaseEventModel;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.validation.BaseValidModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseViewModel implements Serializable{

    private String view_id;
    public static final String VIEW_ID="view_id";

    private String view_type;
    public static final String VIEW_TYPE="view_type";

    private String view_name;
    public static final String VIEW_NAME="view_name";

    private String view_width;
    public static final String VIEW_WIDTH="view_width";

    private String view_height;
    public static final String VIEW_HEIGHT="view_height";

    private List<BaseViewModel> childs;//子view
    public static final String CHILDS="childs";

    private int [] view_of={-1,-1,-1,-1};
    public static final String VIEW_OF="view_of";//相对某些控件的位置 [左，上，右，下]（默认为-1），元素是view_id

    private int [] view_margins={0,0,0,0};//四个方向的外边距 [左，上，右，下]（默认为0），元素是像素值
    public static final String VIEW_MARGINS="view_margins";

    private int [] view_paddings={0,0,0,0};//四个方向的内边距 [左，上，右，下]（默认为0），元素是像素值
    public static final String VIEW_PADDINGS="view_paddings";

    private String event_type;//事件的触发类型；常用的有click,longclick,scroll,slide这几种
    public static final String EVENT_TYPE="event_type";


    private String key;//作为一个预留字段,在VgTextView;VgTextField;VgRadionButton;VgCheckBox;VgSwitchView中 一般作为action 中的请求参数的key
    public static final String KEY="res_key";

    private String action;//动作指向
    public static final String ACTION="action";

    private String actionType;//动作类型
    public static final String ACTION_TYPE="action_type";

//    private List<Integer> refs;//动作依赖的View_id;通过view_id获取对应的View控件
    RefsBean refs;
    public static final String REFS="refs";

    private String bg_normal_color;
    public static final String BG_NORMAL_COLOR="bg_normal_color";

    private String bg_focus_color;
    public static final String BG_FOCUS_COLOR="bg_focus_color";


    private BaseEventModel actionLink;
    public static final String ACTION_LINK="actionLink";

    private List<BaseValidModel> validLink;
    public static final String VALID_LINK="validation";



//    private String gravity;
//    public static final String GRAVITY="gravity";

//    private List<Integer> centers;
//    public static final String CENTERS="centers";

//    private int  orientation;
//    public static final String ORIENTATION="orientation";

    public int getView_id() {
        if(view_id==null||view_id.equals("")||view_id.equals("null")){
            new Exception("view_id 不能为空"+view_id);
            return 0;
        }
        if(!AppUtil.isNumeric(view_id)){
            new Exception("view_id 不能为非数字字符"+view_id);
            return 0;
        }
        return Integer.parseInt(view_id);
    }

    public void setView_id(String view_id) {
        this.view_id = view_id;
    }

    public int getView_type() {
        if(view_type==null||view_type.equals("")||view_type.equals("null")){
            new Exception("view_type 不能为空"+view_type);
            return 9999;
        }
        if(!AppUtil.isNumeric(view_type)){
            new Exception("view_type 不能为非数字字符"+view_type);
            return 9999;
        }
        return Integer.parseInt(view_type);
    }

    public void setView_type(String view_type) {
        this.view_type = view_type;
    }

    public String getView_name() {
        return view_name;
    }

    public void setView_name(String view_name) {
        this.view_name = view_name;
    }

    public List<BaseViewModel> getChilds() {
        if(childs==null||childs.isEmpty()){
            return new ArrayList<>();
        }
        return childs;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setChilds(List<BaseViewModel> childs) {
        this.childs = childs;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionType() {
        if(actionType==null||actionType.equals("null")){
            return "";
        }
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public RefsBean getRefs() {
        if(refs==null){
            refs=new RefsBean();
            return refs;
        }
        return refs;
    }

    public void setRefs(List<String> refS) {
        if(refS==null||refS.isEmpty()){
            return;
        }
        if(refs==null){
            refs=new RefsBean();
        }
        if(!refs.getRefsId().isEmpty()){
            refs.getRefsId().clear();

        }
        if(!refs.getRefsKey().isEmpty()){
            refs.getRefsKey().clear();
        }
        List<Integer> refsId=new ArrayList<>();
        List<String> refsKey=new ArrayList<>();
        for(int i = 0;i<refS.size();i++) {
            if(refS.get(i)==null||refS.get(i).equals("")||refS.get(i).equals("null")){
                new Exception("refs 不能为无效"+refS.get(i));
                return;
            }
            try {
                String []split=refS.get(i).split("\\_");
                if(!AppUtil.checkNumber(split[0], "")){
                    new Exception("refs 不能为非数字字符"+refS.get(i));
                    return;
                }
                refsId.add(Integer.parseInt(split[0]));
                refsKey.add(split[1]);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        refs.setRefsId(refsId);
        refs.setRefsKey(refsKey);
    }

    private View vgView;

    public <T extends View> T getVgView(){
        return (T)vgView;
    }
    public void setVgView(View view){
        vgView=view;
    }

    public int getView_width() {
        if(view_width==null||view_width.equals("")||view_width.equals("null")){
            new Exception("view_width 不能为空"+view_width);
            return 0;
        }
        if(!AppUtil.isNumeric(view_width)){
            new Exception("view_width 不能为非数字字符"+view_width);
            return 0;
        }
//        if(view_width.equals("1080")){
//            return AppUtil.getScreenWidth()
//        }
        return Integer.parseInt(view_width);
    }

    public void setView_width(String view_width) {
        this.view_width = view_width;
    }

    public int getView_height() {
        if(view_id==null||view_id.equals("")||view_id.equals("null")){
            new Exception("view_height 不能为空"+view_height);
            return 0;
        }
        if(!AppUtil.isNumeric(view_id)){
            new Exception("view_height 不能为非数字字符"+view_height);
            return 0;
        }
        return Integer.parseInt(view_height);
    }

    public void setView_height(String view_height) {
        this.view_height = view_height;
    }

    public int[] getView_of() {
        if(view_of==null||view_of.length<1){
            return new int[]{-1,-1,-1,-1};
        }
        return view_of;
    }

    public void setView_of(String[] viewOf) {
        if(viewOf==null||viewOf.length!=4){
            new Exception("view_of 无效");
            return;
        }
        for(int i = 0;i<viewOf.length;i++) {
            if(viewOf[i]==null||viewOf[i].equals("")||viewOf[i].equals("null")){
                new Exception("view_of 不能为无效"+viewOf[i]);
                view_of[i]=-1;
                continue;
            }
            if(!AppUtil.checkNumber(viewOf[i],"")){
                new Exception("view_of 不能为非数字字符"+viewOf[i]);
                view_of[i]=-1;
                continue;
            }
            view_of[i] = Integer.parseInt(viewOf[i]);
        }
    }

    public int[] getView_margins() {
        if(view_margins==null||view_margins.length<1){
            return new int[]{0,0,0,0};
        }
        return view_margins;
    }

    public void setView_margins(String[] viewMargins) {
        if(viewMargins==null||viewMargins.length!=4){
            new Exception("view_paddings 无效").printStackTrace();
            return;
        }
        for(int i = 0;i<viewMargins.length;i++) {
            if(viewMargins[i]==null||viewMargins[i].equals("")||viewMargins[i].equals("null")){
                new Exception("view_paddings 不能为无效"+viewMargins[i]).printStackTrace();
                return;
            }
            if(!AppUtil.checkNumber(viewMargins[i],"")){
                new Exception("view_height 不能为非数字字符"+viewMargins[i]);
                return;
            }
            view_margins[i] = Integer.parseInt(viewMargins[i]);
        }
    }

    public int[] getView_paddings() {
        if(view_paddings==null||view_paddings.length<1){
            return new int[]{0,0,0,0};
        }
        return view_paddings;
    }

    public void setView_paddings(String[] viewPaddings) {
        if(viewPaddings==null||viewPaddings.length!=4){
            new Exception("view_paddings 无效");
            return;
        }
        for(int i = 0;i<viewPaddings.length;i++) {
            if(viewPaddings[i]==null||viewPaddings[i].equals("")||viewPaddings[i].equals("null")){
                new Exception("view_paddings 不能为无效"+viewPaddings[i]);
                return;
            }
            if(!AppUtil.checkNumber(viewPaddings[i],"")){
                new Exception("view_height 不能为非数字字符"+viewPaddings[i]);
                return;
            }
            view_paddings[i] = Integer.parseInt(viewPaddings[i]);
        }
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getBg_normal_color() {
        if(bg_normal_color==null||bg_normal_color.equals("")||bg_normal_color.equals("null")){
            return "#00000000";//透明
        }
        return bg_normal_color;
    }

    public void setBg_normal_color(String bg_normal_color) {
        this.bg_normal_color = bg_normal_color;
    }

    public String getBg_focus_color() {
        if(bg_focus_color==null||bg_focus_color.equals("")||bg_focus_color.equals("null")){
            return getBg_normal_color();
        }
        return bg_focus_color;
    }

    public void setBg_focus_color(String bg_focus_color) {
        this.bg_focus_color = bg_focus_color;
    }

    public BaseEventModel getActionLink() {
        return actionLink;
    }

    public void setActionLink(BaseEventModel actionLink) {
        this.actionLink = actionLink;
    }


    //
//    public String getGravity() {
//        if(gravity==null||gravity.equals("")||gravity.equals("null")){
//            return "left";
//        }
//        return gravity;
//    }
//
//    public void setGravity(String gravity) {
//        this.gravity = gravity;
//    }
//
//    public List<Integer> getCenters() {
//        List<Integer> centerList=new ArrayList<>();
//        if(centers==null||centers.isEmpty()){
//            return centerList;
//        }
//
//
//        return centerList;
//    }
//
//    public void setCenters(List<String> centers) {
//        if(centers==null||centers.isEmpty()){
//            return;
//        }
//        if(this.centers==null){
//            this.centers=new ArrayList<>();
//        }else{
//            if(!this.centers.isEmpty()){
//                this.centers.clear();
//            }
//        }
//        for(int i=0;i<centers.size();i++){
//            if(centers.get(i)==null||centers.get(i).equals("")||centers.get(i).equals("null")){
//                new Exception("view_paddings 不能为无效"+centers.get(i));
//                continue;
//            }
//            if(!AppUtil.checkNumber(centers.get(i),"")){
//                new Exception("view_height 不能为非数字字符"+centers.get(i));
//                continue;
//            }
//            this.centers.add(Integer.parseInt(centers.get(i)));
//        }
//    }
//
//    public int getOrientation() {
//        return orientation;
//    }
//
//    public void setOrientation(String orientation) {
//        if(orientation==null||orientation.equals("")||orientation.equals("null")){
//            orientation="0";
//        }
//        if(!AppUtil.isNumeric(orientation)){
//            orientation="0";
//        }
//        this.orientation = Integer.parseInt(orientation);
//    }


    public List<BaseValidModel> getValidLink() {
        if(validLink==null){
            return null ;
        }
        return validLink;
    }

    public void setValidLink(List<BaseValidModel> validLink) {
        this.validLink = validLink;
    }
}
