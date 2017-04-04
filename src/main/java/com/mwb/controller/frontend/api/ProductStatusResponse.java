package com.mwb.controller.frontend.api;


import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.frontend.api.ResourceVO;

import java.util.List;

/**
 *  Created by Administrator on 2017/4/4 0004.
 */
public class ProductStatusResponse extends ServiceResponse{
    private List<ResourceVO> status;

    public List<ResourceVO> getStatus() {
        return status;
    }

    public void setStatus(List<ResourceVO> status) {
        this.status = status;
    }
}
