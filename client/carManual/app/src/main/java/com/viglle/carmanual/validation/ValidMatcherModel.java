package com.viglle.carmanual.validation;

/**
 * Created by Administrator on 2016/5/13.
 */
public class ValidMatcherModel extends BaseValidModel{
    private String rule;
    public static final String RULE="rule";

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
