package com.mwb.controller.position.api;

import java.util.ArrayList;
import java.util.List;

import com.mwb.dao.model.position.Position;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public class PositionVO {
    private Integer id;
    private String name;

    public static List<PositionVO> toVOs(List<Position> positions){
        List<PositionVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(positions)){
            for (Position position : positions){
                PositionVO vo = toVO(position);
                vos.add(vo);
            }
        }
        return vos;
    }

    public static PositionVO toVO(Position position){
        PositionVO vo = new PositionVO();
        vo.setId(position.getId());
        vo.setName(position.getName());
        return vo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
