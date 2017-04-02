package com.mwb.service.position.api;

import java.util.List;

import com.mwb.dao.filter.PositionFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.permission.Permission;
import com.mwb.dao.model.position.Position;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public interface IPositionService {

    public SearchResult<Position> searchPosition(PositionFilter filter);

    public List<Position> searchAllPosition();

    public List<Permission> searchAllPermission();

    public List<Permission> getPermissionsByPositionId();

    public void createPositionPermission(Integer positionId, List<Integer> permissions);


}
