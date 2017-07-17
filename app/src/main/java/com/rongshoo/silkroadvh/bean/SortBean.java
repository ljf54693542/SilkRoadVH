package com.rongshoo.silkroadvh.bean;

import java.io.Serializable;

/**
 * Created by liaoj on 2017/6/29.
 */

public class SortBean implements Serializable {
    private String name;
    private int type;
    private Boolean isChecked;

    public SortBean(String name, int type, Boolean isChecked) {
        this.name = name;
        this.type = type;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
