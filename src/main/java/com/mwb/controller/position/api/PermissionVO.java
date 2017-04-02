package com.mwb.controller.position.api;

import java.util.ArrayList;
import java.util.List;

import com.mwb.dao.model.permission.Permission;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public class PermissionVO {
    private int id;
    private String code;
    private String name;

    public static List<PermissionVO> toVOs (List<Permission> permissions){
        List<PermissionVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(permissions)){
            for (Permission permission : permissions){
                PermissionVO vo = PermissionVO.toVO(permission);
                vos.add(vo);
            }
        }
        return vos;

    }

    public static PermissionVO toVO(Permission permission){
        PermissionVO vo = new PermissionVO();
        vo.setName(permission.getName());
        vo.setCode(permission.getCode());
        vo.setId(permission.getId());
        return vo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
