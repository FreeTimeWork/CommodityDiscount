package com.mwb.dao.mapper;

import java.util.List;

import com.mwb.dao.model.permission.Permission;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Fangchen.chai on 2017/4/1.
 */
public interface PermissionMapper {
    public List<Permission> selectPermissionsByPositionId(@Param("positionId") int positionId);

    public void deletePermissionByPositionIdAndPermissionId(@Param("positionId") int positionId, @Param("permissionId") int permissionId);

    public void batchInsertPositionPermissions(@Param("positionId") int positionId,  @Param("permissionIds") List<Integer> permissionIds);

    public List<Permission> selectAllPermissions();
}
