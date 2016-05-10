package com.viglle.carmanual.seletor;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Selection;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/4/15.
 */
public class SelectorFactory {

    private static SelectorFactory instance=null;
    private SelectorFactory(){}
    public static SelectorFactory getInstance(){
        if (instance==null){
            instance=new SelectorFactory();
        }
        return instance;
    }
    /**
     *
     * @param context
     * @param normalDrawable
     * @param pressDrawable
     * @param focusedDrawable
     * @param unableDrawable
     * @return
     */
    public  StateListDrawable newSelector(Context context,
                                                      Drawable normalDrawable,
                                                      Drawable pressDrawable,
                                                      Drawable focusedDrawable,
                                                      Drawable unableDrawable) {
        StateListDrawable bg = new StateListDrawable();
        ColorDrawable colorDrawable=new ColorDrawable();
        colorDrawable.setColor(Color.parseColor(ColorConstant.STATE_NORMAL_COLOR));
        Drawable normal = normalDrawable == null ? colorDrawable : normalDrawable;
        Drawable pressed = pressDrawable == null ? colorDrawable : pressDrawable;
        Drawable focused = focusedDrawable == null ? colorDrawable : focusedDrawable;
        Drawable unable = unableDrawable ==null ? colorDrawable : unableDrawable;
        // View.PRESSED_ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressed);
        // View.ENABLED_FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, focused);
        // View.ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled }, normal);
        // View.FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_focused }, focused);
        // View.WINDOW_FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_window_focused }, unable);
        // View.EMPTY_STATE_SET
        bg.addState(new int[] {}, normal);
        return bg;
    }

    public  StateListDrawable newSeletor(Context ctx,String normalColor,String pressedColor,String focusedColor,String unableColor){
        StateListDrawable bg = new StateListDrawable();

        ColorDrawable normal=setStateColor(normalColor,ColorConstant.STATE_NORMAL_COLOR);
        ColorDrawable pressed=setStateColor(pressedColor,ColorConstant.STATE_PRESS_COLOR);
        ColorDrawable focused=setStateColor(focusedColor,ColorConstant.STATE_FOCUS_COLOR);
        ColorDrawable unable =setStateColor(unableColor,ColorConstant.STATE_UNABLE_COLOR);
        bg.addState(new int[]{android.R.attr.state_enabled,android.R.attr.state_pressed},pressed);
        bg.addState(new int[]{android.R.attr.state_enabled,android.R.attr.state_focused},focused);
        bg.addState(new int[]{android.R.attr.state_enabled},normal);
        bg.addState(new int[]{android.R.attr.state_focused},focused);
        bg.addState(new int[]{android.R.attr.state_window_focused},unable);
        return bg;
    }

    private ColorDrawable setStateColor(String color, String  defaultColor) {
        ColorDrawable colorDrawable=new ColorDrawable();
        if (color == null || color.equals("") || color.equals("null")|| !AppUtil.isColor(color)) {
            colorDrawable.setColor(Color.parseColor(defaultColor));
        } else {
            colorDrawable.setColor(Color.parseColor(color));
        }
        return colorDrawable;
    }

    ;
}
