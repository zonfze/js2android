package com.viglle.carmanual.widget.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/4/12.
 */
public class VgTextFieldModel extends BaseTextViewModel {
//    private String text;//要显示的数据
//    public static final String TEXT="text";
//
//    private String text_color;//文本颜色
//    public static final  String TEXT_COLOR="text_color";
//
//    private String text_size;//文本大小
//    public static final String TEXT_SIZE="text_size";
//
//    private String text_align="0";//文本对齐方式:1 左对齐;2 右对齐;0 居中对齐
//    public static final String TEXT_ALIGN="text_align";

    private String password="0";//是否以密文方式显示:0代表明文显示;1代表密文显示;
    public static final String PASSWORD="password";

    private String inputType="1";//1,全部;2 字母;3 数字;4 电话号码
    public static final String INPUTTYPE="input_type";

    private String hint;
    public static final String HINT="hint";


//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getInputType() {
        if(inputType==null||inputType.equals("")||inputType.equals("null")){
            new Exception("inputType 不能为空"+inputType);
            return 1;
        }
        if(!AppUtil.isNumeric(inputType)){
            new Exception("inputType 不能为非数字字符"+inputType);
            return 1;
        }
        return Integer.parseInt(inputType);
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
//
//    public int getText_size() {
//        if(text_size==null||text_size.equals("")||text_size.equals("null")){
//            new Exception("text_size 不能为空"+text_size);
//            return 1;
//        }
//        if(!AppUtil.isNumeric(text_size)){
//            new Exception("text_size 不能为非数字字符"+text_size);
//            return 1;
//        }
//        return Integer.parseInt(text_size);
//    }
//
//    public void setText_size(String text_size) {
//        this.text_size = text_size;
//    }
//
//    public String getText_color() {
//        if(text_color==null||text_color.equals("")||text_color.equals("null")){
//            return "#ff000000";
//        }
//        if(!AppUtil.isColor(text_color)){
//            return "#ff000000";
//        }
//        return text_color;
//    }
//
//    public void setText_color(String text_color) {
//        this.text_color = text_color;
//    }
//
//    public int getText_align() {
//        if(text_align==null||text_align.equals("")||text_align.equals("null")){
//            new Exception("text_align 不能为空"+text_align);
//            return 0;
//        }
//        if(!AppUtil.isNumeric(text_align)){
//            new Exception("text_align 不能为非数字字符"+text_align);
//            return 0;
//        }
//        return Integer.parseInt(text_align);
//    }
//
//    public void setText_align(String text_align) {
//        this.text_align = text_align;
//    }

    public int getPassword() {
        if(password==null||password.equals("")||password.equals("null")){
            new Exception("password 不能为空"+password);
            return 0;
        }
        if(!AppUtil.isNumeric(password)){
            new Exception("password 不能为非数字字符"+password);
            return 0;
        }
        return Integer.parseInt(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
