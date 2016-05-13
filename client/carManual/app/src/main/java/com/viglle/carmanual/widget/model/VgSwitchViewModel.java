package com.viglle.carmanual.widget.model;

/**
 * Created by Administrator on 2016/5/12.
 */
public class VgSwitchViewModel extends BaseViewModel{
    private String isChecked;//选中状态;默认 0 未选中;1代表选中
    public static final String ISCHECKED="isChecked";

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
}
