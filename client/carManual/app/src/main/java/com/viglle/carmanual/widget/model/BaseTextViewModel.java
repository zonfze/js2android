package com.viglle.carmanual.widget.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/4/22.
 */
public abstract class BaseTextViewModel extends BaseViewModel{
    private String text;
    public static final String TEXT="text";

    private String text_color;//文本颜色
    public static final  String TEXT_COLOR="text_color";

    private String text_size;//文本大小
    public static final String TEXT_SIZE="text_size";

    private String text_align="0";//文本对齐方式:1 左对齐;2 右对齐;0 居中对齐
    public static final String TEXT_ALIGN="text_align";

    private String drawableLeft;
    public static final String DRAWABLE_LEFT="drawableLeft";
    private String drawableTop;
    public static final String DRAWABLE_TOP="drawableTop";
    private String drawableRight;
    public static final String DRAWABLE_RIGHT="drawableRight";
    private String drawableBottom;
    public static final String DRAWABLE_BOTTOM="drawableBottom";
    private String drawablePadding;
    public static final String DRAWABLE_PADDING="drawablePadding";




    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText_color() {
        if(text_color==null||text_color.equals("")||text_color.equals("null")){
            new Exception("text_color 非法"+text_color);
            return "#ff000000";
        }
        if(!AppUtil.isColor(text_color)){
            return "#ff000000";
        }
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public int getText_size() {
        if(text_size==null||text_size.equals("")||text_size.equals("null")){
            new Exception("text_size 不能为空"+text_size);
            return 1;
        }
        if(!AppUtil.isNumeric(text_size)){
            new Exception("text_size 不能为非数字字符"+text_size);
            return 1;
        }
        return Integer.parseInt(text_size);
    }

    public void setText_size(String text_size) {
        this.text_size = text_size;
    }

    public int getText_align() {
        if(text_align==null||text_align.equals("")||text_align.equals("null")){
            new Exception("text_align 不能为空"+text_align);
            return 0;
        }
        if(!AppUtil.isNumeric(text_align)){
            new Exception("text_align 不能为非数字字符"+text_align);
            return 0;
        }
        return Integer.parseInt(text_align);
    }

    public void setText_align(String text_align) {
        this.text_align = text_align;
    }

    public String getDrawableLeft() {
        return drawableLeft;
    }

    public void setDrawableLeft(String drawableLeft) {
        this.drawableLeft = drawableLeft;
    }

    public String getDrawableTop() {
        return drawableTop;
    }

    public void setDrawableTop(String drawableTop) {
        this.drawableTop = drawableTop;
    }

    public String getDrawableRight() {
        return drawableRight;
    }

    public void setDrawableRight(String drawableRight) {
        this.drawableRight = drawableRight;
    }

    public String getDrawableBottom() {
        return drawableBottom;
    }

    public void setDrawableBottom(String drawableBottom) {
        this.drawableBottom = drawableBottom;
    }

    public String getDrawablePadding() {
        return drawablePadding;
    }

    public void setDrawablePadding(String drawablePadding) {
        this.drawablePadding = drawablePadding;
    }
}
