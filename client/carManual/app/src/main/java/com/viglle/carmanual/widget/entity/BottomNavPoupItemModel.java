package com.viglle.carmanual.widget.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class BottomNavPoupItemModel implements Serializable{
    private String labelId;//菜单的唯一标识
    public static final String LABEL_ID="labelid";

    private String title;//导航标签的文字
    public static final String TAB_TEXT="title";

    private List<BottomNavPoupItemModel> subMenu=new ArrayList<>();
    public static final String SUB_MENU="sub_menu";//子菜单

    public String url;
    public static final String URL="url";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public List<BottomNavPoupItemModel> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<BottomNavPoupItemModel> submenu) {
        if(submenu==null||submenu.isEmpty()){
            return;
        }
        if(subMenu==null||subMenu.isEmpty()){
            subMenu.clear();
        }
        this.subMenu.addAll(submenu);
    }

//    public static class ItemModel{
//        private String subLabelId;//子菜单菜单的唯一标识
//        public static final String SUB_LABEL_ID="sublabelid";
//
//        private String subtitle;//子菜单导航标签的文字
//        public static final String SUBTITLE="subtitle";
//
//        public String url;
//        public static final String SUB_URL="url";
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//        public String getSubtitle() {
//            return subtitle;
//        }
//
//        public void setSubtitle(String subtitle) {
//            this.subtitle = subtitle;
//        }
//
//        public String getSubLabelId() {
//            return subLabelId;
//        }
//
//        public void setSubLabelId(String subLabelId) {
//            this.subLabelId = subLabelId;
//        }
//    }

    @Override
    public String toString() {
        return "BottomNavPoupItemModel{" +
                "labelId='" + labelId + '\'' +
                ", title='" + title + '\'' +
                ", subMenu=" + subMenu +
                ", url='" + url + '\'' +
                '}';
    }
}
