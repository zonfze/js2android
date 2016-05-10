package com.viglle.carmanual.action;

/**
 * Created by Administrator on 2016/5/3.
 */
public class ActionType {
    public static final int ACTION_SHOW_TOAST=10001;//显示Toast提示
    public static final int ACTION_SHOW_SNACKBAR=10002;//显示Snackbar提示

    public static final int ACTION_OBTAIN_PARAM_FROM_SDCARD=10003;//从本地获取参数
    public static final int ACTION_SAVE_PARAM_TO_SDCARD=10004;//保存数据到本地

    public static final int ACTION_OBTAIN_PARAM_FROM_VIEW=10005;//获取控件的值
    public static final int ACTION_UPDATE_VIEW=10006;//刷新UI(可能是一个也可能使多个UI(控件的显示/隐藏/背景颜色改变/动画效果/值的改变(清空,更改)))

    public static final int ACTION_NEW_PANEL=10007;//打开一个新的界面
    public static final int ACTION_CLOSE_PANEL=10008;//关闭一个界面(类似于返回)
    public static final int ACTION_CLOSE_AND_NEW_PANEL=10009;//关闭上一个界面并打开新界面



    public static final int ACTION_COUNT_TIMER=10010;//计时
    public static final int ACTION_COUNT_DOWN_TIMER=10011;//倒计时(有些界面倒计时完成后自动关闭,或者倒计时后没有操作就采用默认配置)

    public static final int ACTION_SHOW_DIALOG=10012;//显示对话框
    public static final int ACTION_DIMISS_DIALOG=10013;//隐藏对话框

    public static final int ACTION_SEND_HTTP_REQUEST=10014;//发送http请求
}
