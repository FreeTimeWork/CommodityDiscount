package com.mwb.dao.mapper;

import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.model.finance.Finance;
import com.mwb.dao.model.product.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public interface FinanceMapper {

    public int countFinanceByFilter(@Param("filter") FinanceFilter filter);

    public List<Finance> selectFinanceByFilter(@Param("filter") FinanceFilter filter);

    public Finance selectFinanceByEmployeeId(@Param("employeeId") Integer employeeId);

    public int selectCurrentFinanceRank(@Param("employeeId") Integer employeeId);

    public void updateFinance(Finance finance);

    public void insertFinance(Finance finance);

}
