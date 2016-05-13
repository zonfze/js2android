package com.viglle.carmanual.widget.model;

/**
 * Created by Administrator on 2016/5/12.
 */
public class ButtonCompoudModel extends BaseTextViewModel{
    private String isChecked="0";//选中状态;默认 0 未选中;1代表选中
    public static final String ISCHECKED="isChecked";


    private String buttonNormalState;
    public static final String BTN_NORMAL_STATE="buttonNormalState";
    private String buttonFocusState;
    public static final String BTN_FOCUS_STATE="buttonFocusState";

    public boolean isChecked() {
        if(isChecked==null||isChecked.equals("")||isChecked.equalsIgnoreCase("null")||isChecked.equals("0")){
            return false;
        }else{
            return true;
        }
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getButtonNormalState() {
        return buttonNormalState;
    }

    public void setButtonNormalState(String buttonNormalState) {
        this.buttonNormalState = buttonNormalState;
    }

    public String getButtonFocusState() {
        return buttonFocusState;
    }

    public void setButtonFocusState(String buttonFocusState) {
        this.buttonFocusState = buttonFocusState;
    }
}
