package com.viglle.carmanual.validation;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/13.
 */
public class ValidLengthModel extends BaseValidModel{
    private String max;
    private String min;
    public static final String MAX="max";
    public static final String MIN="min";

    public int getMax() {
        if(max==null||max.equals("")||max.equalsIgnoreCase("null")){
            return -1;
        }
        if(!AppUtil.isNumeric(max)){
            return -1;
        }
        return Integer.parseInt(max);
    }

    public void setMax(String max) {
        this.max = max;
    }

    public int getMin() {
        if(min==null||min.equals("")||min.equalsIgnoreCase("null")){
            return -1;
        }
        if(!AppUtil.isNumeric(min)){
            return -1;
        }
        return Integer.parseInt(min);
    }

    public void setMin(String min) {
        this.min = min;
    }
}
