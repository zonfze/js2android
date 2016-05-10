package com.viglle.carmanual.viewfactory;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.viglle.carmanual.action.EventType;
import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.action.model.BaseEventModel;
import com.viglle.carmanual.action.model.EventAutoModel;
import com.viglle.carmanual.action.model.EventClickModel;
import com.viglle.carmanual.action.model.EventTimerModel;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class EventFactory {

    public static void createEvent(Context ctx,View view, BaseViewModel model){
        if(model==null){//在使用参数之前,先校验参数是否异常
            return;
        }
        BaseEventModel eventModel=model.getActionLink();
        createActionLink(ctx, view, eventModel);
    }

    public static void createActionLink(Context ctx, View view, BaseEventModel eventModel) {
        if(eventModel==null){
            return;
        }
        int event_type=eventModel.getEventType();
        switch (event_type){
            case EventType.AUTO_DRIVE://自动触发
                EventAutoModel autoModel= (EventAutoModel) eventModel;
                createAutoStart(ctx,view,autoModel);
                break;
            case EventType.TIME_DRIVE://倒计时触发
                EventTimerModel eventTimerModel= (EventTimerModel) eventModel;
                createEventTimer(ctx,view,eventTimerModel);
                break;
            case EventType.TOUCH_CLICK_DRIVE://点击触发
                EventClickModel eventClickModel= (EventClickModel) eventModel;
                createEventClick(ctx, view, eventClickModel);
                break;
            case EventType.TOUCH_DRAG_DRIVE://拖曳

                break;
            case EventType.TOUCH_FLIP_DRIVE://轻触快速划过

                break;
            case EventType.TOUCH_LONG_CLICK_DRIVE://长按

               break;
            case EventType.TOUCH_SCROLL_DRIVE://滚动

                break;
        }
    }


    public static void createAutoStart(final Context ctx,final View view, final EventAutoModel model){
        if(model==null){//首先校验参数是否异常;异常则返回
            LogUtil.log_d("EventAutoModel is null");
            return;
        }
        ActionFactory.createActionLink(ctx,view,model.getLink());
    }

    public static void createEventClick(final Context ctx,final View view, final EventClickModel model){
        if(model==null){//首先校验参数是否异常;异常则返回
            LogUtil.log_d("EventClickModel is null");
            return;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BaseActionModel> link= model.getLink();
                ActionFactory.createActionLink(ctx,view,link);
            }
        });
    }

    public static void createEventTimer(final Context ctx, final View view, final EventTimerModel model){
        if(model==null){//首先校验参数是否异常;异常则返回
            LogUtil.log_d("EventTimerModel is null");
            return;
        }

        final long time=model.getTime();
        new AsyncTask<String,Integer,Boolean>(){
            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if(aBoolean){
                    List<BaseActionModel> links= model.getLink();
                    ActionFactory.createActionLink(ctx,view,links);
                }
            }
        }.execute("");
    }
}
