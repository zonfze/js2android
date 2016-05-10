package com.viglle.carmanual.widget.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
public class RefsBean {
    List<Integer> mRefsId=new ArrayList<>();
    List<String> mRefsKey=new ArrayList<>();

    public List<Integer> getRefsId() {
        return mRefsId;
    }

    public void setRefsId(List<Integer> refsId) {
        if(refsId==null||refsId.isEmpty()){
            return;
        }
        if(!mRefsId.isEmpty()){
            mRefsId.clear();
        }
        this.mRefsId.addAll(refsId);
    }

    public List<String> getRefsKey() {
        return mRefsKey;
    }

    public void setRefsKey(List<String> refsKey) {
        if(refsKey==null||refsKey.isEmpty()){
            return;
        }
        if(!mRefsKey.isEmpty()){
            mRefsKey.clear();
        }
        this.mRefsKey.addAll(refsKey);
    }
}
