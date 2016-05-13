package com.viglle.carmanual.factory;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.viglle.carmanual.action.EventType;
import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.action.model.BaseEventModel;
import com.viglle.carmanual.action.model.EventAutoModel;
import com.viglle.carmanual.action.model.EventClickModel;
import com.viglle.carmanual.action.model.EventTimerModel;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.widget.entity.ViewTreeBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class EventFactory {

    public static void createEventLink(Context ctx, ViewTreeBean viewTreeBean, List<BaseEventModel> eventModels){
        if(eventModels==null||eventModels.isEmpty()){//校验参数是否合法
            return;
        }
        for(BaseEventModel model:eventModels){
            createEvent(ctx, viewTreeBean, model);
        }
    }

    public static void createEvent(Context ctx, ViewTreeBean viewTreeBean, BaseEventModel eventModel) {
        if(eventModel==null){//校验参数是否合法
            return;
        }
        int event_type=eventModel.getEventType();
        switch (event_type){
            case EventType.AUTO_DRIVE://自动触发
                EventAutoModel autoModel= (EventAutoModel) eventModel;
                createAutoStart(ctx,null,autoModel);
                break;
            case EventType.TIME_DRIVE://倒计时触发
                EventTimerModel eventTimerModel= (EventTimerModel) eventModel;
                createEventTimer(ctx,null,eventTimerModel);
                break;
            case EventType.TOUCH_CLICK_DRIVE://点击触发
                EventClickModel eventClickModel= (EventClickModel) eventModel;
                createEventClick(ctx, viewTreeBean, eventClickModel);
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


    public static void createAutoStart(final Context ctx,ViewTreeBean viewTreeBean, final EventAutoModel model){
        if(model==null){//首先校验参数是否异常;异常则返回
            LogUtil.log_d("EventAutoModel is null");
            return;
        }
        ActionFactory.createActionLink(ctx,viewTreeBean,model.getActionLink());
    }

    public static void createEventClick(final Context ctx,final ViewTreeBean viewTreeBean, final EventClickModel model){
        if(model==null){//首先校验参数是否异常;异常则返回
            LogUtil.log_d("EventClickModel is null");
            return;
        }
        final View view=viewTreeBean.getViewById(model.getView_id());
        if(view==null){//避免空指针异常
            return;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BaseActionModel> link= model.getActionLink();
                ActionFactory.createActionLink(ctx,viewTreeBean,link);
            }
        });
    }

    public static void createEventTimer(final Context ctx, final ViewTreeBean viewTreeBean, final EventTimerModel model){
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
                    if(ctx==null){
                        return;
                    }
                    List<BaseActionModel> links= model.getActionLink();
                    ActionFactory.createActionLink(ctx,viewTreeBean,links);
                }
            }
        }.execute("");
    }
}
