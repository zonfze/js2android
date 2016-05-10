package com.viglle.carmanual.parsor;

import com.viglle.carmanual.action.EventType;
import com.viglle.carmanual.action.model.BaseEventModel;
import com.viglle.carmanual.action.model.EventAutoModel;
import com.viglle.carmanual.action.model.EventClickModel;
import com.viglle.carmanual.action.model.EventFlipModel;
import com.viglle.carmanual.action.model.EventLongClickModel;
import com.viglle.carmanual.action.model.EventTimerModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/4.
 */
public class VgEventParsor {

    public static BaseEventModel parsorActionLink(JSONObject obj) throws JSONException {

        if(obj==null){
            return null;
        }
        String objStr=obj.toString();
        if(objStr.equals("{}")||objStr.equals("")||objStr.equals("null")){
            return null;
        }
        String eventTypeStr=obj.getString(BaseEventModel.EVENT_TYPE);
        int event_type=0;
        try {
            event_type =Integer.parseInt(eventTypeStr);
        }catch (Exception  e){
            e.printStackTrace();
            return null;
        }

        switch (event_type){
            case EventType.AUTO_DRIVE://自动触发
                EventAutoModel autoModel=new EventAutoModel();
                autoModel.setEventType(eventTypeStr);
                autoModel.setLink(VgActionParsor.parsorLink(obj));
                return autoModel;
            case EventType.TIME_DRIVE://倒计时触发
                EventTimerModel timerEventModel = new EventTimerModel();
                timerEventModel.setEventType(eventTypeStr);
                timerEventModel.setLink(VgActionParsor.parsorLink(obj));
                timerEventModel.setTime(obj.getString(EventTimerModel.TIME));
                return timerEventModel;
            case EventType.TOUCH_CLICK_DRIVE://点击触发
                EventClickModel eventClickModel = new EventClickModel();
                eventClickModel.setEventType(eventTypeStr);
                eventClickModel.setLink(VgActionParsor.parsorLink(obj));

                return eventClickModel;
            case EventType.TOUCH_DRAG_DRIVE://拖曳

                break;
            case EventType.TOUCH_FLIP_DRIVE://轻触快速划过
                EventFlipModel eventFlipModel=new EventFlipModel();
                eventFlipModel.setEventType(eventTypeStr);
                eventFlipModel.setLink(VgActionParsor.parsorLink(obj));
                return eventFlipModel;
            case EventType.TOUCH_LONG_CLICK_DRIVE://长按
                EventLongClickModel eventLongClickModel = new EventLongClickModel();
                eventLongClickModel.setEventType(eventTypeStr);
                eventLongClickModel.setLink(VgActionParsor.parsorLink(obj));
                return eventLongClickModel;
            case EventType.TOUCH_SCROLL_DRIVE://滚动

                break;
        }
        return null;
    }
}
