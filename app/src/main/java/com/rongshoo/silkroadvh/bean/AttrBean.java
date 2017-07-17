package com.rongshoo.silkroadvh.bean;

import java.util.List;

/**
 * Created by liaoj on 2017/7/11.
 */

public class AttrBean{

    /**
     * key : 大小
     * value : ["s","m"]
     */

    private String key;
    private List<String> value;
    private String choiceStr;

    public String getChoiceStr() {
        return choiceStr;
    }

    public void setChoiceStr(String choiceStr) {
        this.choiceStr = choiceStr;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
