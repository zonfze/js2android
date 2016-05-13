package com.viglle.carmanual.parsor;

import com.viglle.carmanual.action.EventType;
import com.viglle.carmanual.action.model.BaseEventModel;
import com.viglle.carmanual.action.model.EventAutoModel;
import com.viglle.carmanual.action.model.EventClickModel;
import com.viglle.carmanual.action.model.EventFlipModel;
import com.viglle.carmanual.action.model.EventLongClickModel;
import com.viglle.carmanual.action.model.EventTimerModel;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.widget.model.BaseViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class VgEventParsor {
    public static final String EVENT_LINK="eventLink";

    public static BaseEventModel parsorEventModel(JSONObject obj) throws JSONException {
        int event_type =0;
        if(!checkObj(obj)) {
            return null;
        }
        String eventTypeStr=obj.getString(BaseEventModel.EVENT_TYPE);
        if(eventTypeStr==null){//判断获取到的字符串是否合法,避免空指针
            return null;
        }
        if(!AppUtil.isNumeric(eventTypeStr)){//校验字符串是否是由数字组成;避免字符串转数字的时异常
            return null;
        }
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
                autoModel.setActionLink(VgActionParsor.parsorActionLink(obj));
                return autoModel;
            case EventType.TIME_DRIVE://倒计时触发
                EventTimerModel timerEventModel = new EventTimerModel();
                timerEventModel.setEventType(eventTypeStr);
                timerEventModel.setActionLink(VgActionParsor.parsorActionLink(obj));
                timerEventModel.setTime(obj.getString(EventTimerModel.TIME));
                return timerEventModel;
            case EventType.TOUCH_CLICK_DRIVE://点击触发
                EventClickModel eventClickModel = new EventClickModel();
                eventClickModel.setEventType(eventTypeStr);
                eventClickModel.setView_id(obj.getString(BaseViewModel.VIEW_ID));
                eventClickModel.setActionLink(VgActionParsor.parsorActionLink(obj));

                return eventClickModel;
            case EventType.TOUCH_DRAG_DRIVE://拖曳

                break;
            case EventType.TOUCH_FLIP_DRIVE://轻触快速划过
                EventFlipModel eventFlipModel=new EventFlipModel();
                eventFlipModel.setEventType(eventTypeStr);
                eventFlipModel.setActionLink(VgActionParsor.parsorActionLink(obj));
                return eventFlipModel;
            case EventType.TOUCH_LONG_CLICK_DRIVE://长按
                EventLongClickModel eventLongClickModel = new EventLongClickModel();
                eventLongClickModel.setEventType(eventTypeStr);
                eventLongClickModel.setActionLink(VgActionParsor.parsorActionLink(obj));
                return eventLongClickModel;
            case EventType.TOUCH_SCROLL_DRIVE://滚动

                break;
        }
        return null;
    }

    private static boolean checkObj(JSONObject obj) {
        if(obj==null){//参数合法校验
            return false;
        }
        String objStr=obj.toString();
        if(objStr.equals("{}")||objStr.equals("")||objStr.equals("null")){//参数合法校验
            return false;
        }
        return true;
    }

    public static List<BaseEventModel> parsorEventLink(JSONObject obj) throws JSONException {

        if(!checkObj(obj)){
            return null;
        };
        JSONArray array=obj.getJSONArray(EVENT_LINK);
        if(array==null||array.length()<1){
            return null;
        }
        List<BaseEventModel> eventModelList=new ArrayList<>();
        for(int i=0;i<array.length();i++){
            JSONObject object = array.getJSONObject(i);
            eventModelList.add(parsorEventModel(object));
        }

        return eventModelList;
    }

}
