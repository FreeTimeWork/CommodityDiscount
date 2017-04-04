package com.mwb.controller.frontend.api;

/**
 * Created by MengWeiBo on 2017-03-31
 */
public class ResourceVO {
    private String label;
    private Object value;

    public ResourceVO() {
    }

    public ResourceVO(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
