package com.mwb.dao.mapper;

import com.mwb.dao.model.position.Position;

/**
 * Created by Fangchen.chai on 2017/3/31.
 */
public interface PositionMapper {

    public void insertPosition(Position position);

    public void updatePosition(Position position);

    public Position selectPositionById(Integer id);

}
