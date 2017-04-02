package com.mwb.service.position;

import java.util.List;

import com.mwb.dao.filter.PositionFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.permission.Permission;
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

    @Override
    public List<Position> searchAllPosition() {
        return null;
    }

    @Override
    public List<Permission> searchAllPermission() {
        return null;
    }

    @Override
    public List<Permission> getPermissionsByPositionId() {
        return null;
    }

    @Override
    public void createPositionPermission(Integer positionId, List<Integer> permissions) {

    }
}
