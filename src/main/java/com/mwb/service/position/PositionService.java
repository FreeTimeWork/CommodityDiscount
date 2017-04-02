package com.mwb.service.position;

import com.mwb.dao.filter.PositionFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.position.Position;
import com.mwb.service.position.api.IPositionService;
import org.springframework.stereotype.Service;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
@Service("positionService")
public class PositionService implements IPositionService{


    @Override
    public SearchResult<Position> searchPosition(PositionFilter filter) {
        return null;
    }
}
