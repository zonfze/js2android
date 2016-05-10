package com.viglle.carmanual.widget.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/4/18.
 */
public class VgImageViewModel extends BaseViewModel{
    private String src;
    public static final String SRC="imageUrl";//图片路径

    private String scaleType;
    public static final String SCALE_TYPE="scaleType";//0 按图片大小显示;1充满控件;2在控件内部;3如果图片太大久截取部分显示

    public String getSrc() {
        if(src==null||src.equals("null")){
            return "";
        }
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getScaleType() {
        if(scaleType==null||scaleType.equals("")||scaleType.equals("null")){
            new Exception("scaleType 不能为空"+scaleType);
            return 0;
        }
        if(!AppUtil.isNumeric(scaleType)){
            new Exception("view_id 不能为非数字字符"+scaleType);
            return 0;
        }
        return Integer.parseInt(scaleType);
    }

    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }
}
