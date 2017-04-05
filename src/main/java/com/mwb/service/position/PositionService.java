package com.mwb.service.position;

import java.util.List;

import com.mwb.dao.filter.PositionFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.PermissionMapper;
import com.mwb.dao.mapper.PositionMapper;
import com.mwb.dao.model.permission.Permission;
import com.mwb.dao.model.position.Position;
import com.mwb.service.position.api.IPositionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
@Service("positionService")
public class PositionService implements IPositionService{

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public SearchResult<Position> searchPosition(PositionFilter filter) {

        List<Position> positions = positionMapper.selectPositionByFiler(filter);
        SearchResult<Position> result = new SearchResult<>();
        result.setResult(positions);

        return result;
    }

    @Override
    public List<Position> searchAllPosition() {
        return null;
    }

    @Override
    public List<Permission> searchAllPermission() {
        return permissionMapper.selectAllPermissions();
    }

    @Override
    public List<Permission> getPermissionsByPositionId(Integer PositionId) {
        return permissionMapper.selectPermissionsByPositionId(PositionId);
    }


    @Override
   	@Transactional(readOnly=false, propagation= Propagation.REQUIRED)
    public void createPositionPermission(Integer positionId, List<Integer> permissions) {
        if (CollectionUtils.isNotEmpty(permissions)){
            //删除该岗位所有权限
            for (Integer id : permissions) {
                permissionMapper.deletePermissionByPositionIdAndPermissionId(positionId, id);
            }
            //批量添加
            permissionMapper.batchInsertPositionPermissions(positionId, permissions);
        }

    }
}
