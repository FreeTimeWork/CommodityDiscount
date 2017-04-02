package com.mwb.controller.position.api;

import java.util.List;

import com.mwb.controller.api.PagingResponse;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public class SearchPositionResponse extends PagingResponse {
    List<PositionVO> positions;

    public List<PositionVO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionVO> positions) {
        this.positions = positions;
    }
}
