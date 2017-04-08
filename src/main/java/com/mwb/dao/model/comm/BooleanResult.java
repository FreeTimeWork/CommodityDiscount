package com.mwb.dao.model.comm;

/**
 * Created by fangchen.chai on 2017/4/8.
 */

/**
 *  默认为true
 */
public class BooleanResult {
    private boolean result = true;
    private String message;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
