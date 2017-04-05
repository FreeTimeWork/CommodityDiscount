package com.mwb.controller.position.api;

import com.mwb.controller.api.PagingRequest;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public class SearchPositionRequest extends PagingRequest{

    private Integer positionId;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}
