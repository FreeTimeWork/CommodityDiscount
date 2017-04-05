package com.mwb.dao.mapper;

import java.util.List;

import com.mwb.dao.filter.PositionFilter;
import com.mwb.dao.model.position.Position;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Fangchen.chai on 2017/3/31.
 */
public interface PositionMapper {

    public List<Position> selectPositionByFiler(@Param("filter") PositionFilter filter);

    public void insertPosition(Position position);

    public void updatePosition(Position position);

    public Position selectPositionById(Integer id);

}
