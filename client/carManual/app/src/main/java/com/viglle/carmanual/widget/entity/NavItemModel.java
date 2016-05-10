package com.viglle.carmanual.widget.entity;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/4/6.
 */
public class NavItemModel<T> {
    private String labelId;//菜单的唯一标识
    public static final String LABEL_ID="labelid";

    private String title;//导航标签的文字
    public static final String TAB_TEXT="title";

    private String msgNum;//消息条数(红点上的数字)
    public static final String MSG_NUM="msgNum";

    private String unFocusImage;//未选中的状态url地址
    public static final String UN_FOCUS_IMAGE="unFocusImage";

    private String focusImage;//选中状态图片url地址
    public static final String FOCUS_IMAGE="focusImage";

    private T data;//用户自己需要绑定的数据



    public String getLabelId() {
        return labelId;
    }
    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMsgNum() {
        if(msgNum==null||msgNum.equals("")||msgNum.equals("null")){
            new Exception("msgNum 不能为空"+msgNum);
            return 0;
        }
        if(!AppUtil.isNumeric(msgNum)){
            new Exception("msgNum 不能为非数字字符"+msgNum);
            return 0;
        }
        return Integer.parseInt(msgNum);
    }
    public void setMsgNum(String msgNum) {
        this.msgNum = msgNum;
    }

    public String getUnFocusImage() {
        return unFocusImage;
    }
    public void setUnFocusImage(String unFocusImage) {
        this.unFocusImage = unFocusImage;
    }

    public String getFocusImage() {
        return focusImage;
    }
    public void setFocusImage(String focusImage) {
        this.focusImage = focusImage;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
