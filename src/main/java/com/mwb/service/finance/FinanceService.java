package com.mwb.service.finance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.mwb.dao.model.comm.PagingData;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwb.controller.api.PagingResult;
import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.FinanceMapper;
import com.mwb.dao.mapper.ProductMapper;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.finance.Finance;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.service.finance.api.IFinanceService;
import com.mwb.util.DateTimeUtility;

/**
 * Created by MengWeiBo on 2017-04-01
 */
@Service("financeService")
public class FinanceService implements IFinanceService {

    @Autowired
    private FinanceMapper financeMapper;

    @Autowired
    private ProductMapper productMapper;

    public SearchResult<Finance> searchFinance(final FinanceFilter filter, Employee employee) {
        SearchResult<Finance> result = new SearchResult<>();

        List<Finance> finances = new ArrayList<>();
        if (employee != null) {
            Integer positionId = employee.getPosition().getId();
            if (positionId.equals(2) || positionId.equals(6)) {
                filter.setEmployeeId(employee.getId());
            } else if (positionId.equals(3)) {
                filter.setGroupId(employee.getGroup().getId());
            } else if (positionId.equals(4)) {
                return result;
            }
        }

        List<Product> products = productMapper.selectStatisticsProductByFilter(filter);

        if (CollectionUtils.isEmpty(products)) {
            return result;

        }
        Map<Integer, List<Product>> emIdAndPro = new LinkedHashMap<>();
        //根据employeeId分组
        for (Product pro : products) {
            Integer employeeId = pro.getEmployee().getId();
            if (emIdAndPro.get(employeeId) == null) {
                List<Product> emPro = new ArrayList<>();
                emPro.add(pro);
                emIdAndPro.put(employeeId, emPro);
            } else {
                emIdAndPro.get(employeeId).add(pro);
            }
        }

        for (Map.Entry<Integer, List<Product>> entry : emIdAndPro.entrySet()) {
            Employee financeEm = entry.getValue().get(0).getEmployee();
            Finance finance = new Finance();
            Integer submitNumber = 0;//提报数量
            Integer averageDaily;//提报率  **
            Integer refuseRate;//拒绝率 **
            Integer refuseNumber = 0;//拒绝数量
            Integer twoAuditNumber = 0;//待二审数量
            Integer promoteNumber = 0;//推广中数量
            Integer endApproachNumber = 0;//即将结束数量
            Integer endNumber = 0;//结束数量
            Integer payWaitNumber = 0;//代付款数量
            Integer payRunNumber = 0;//付款中数量
            Integer payTrailerNumber = 0;//拒绝付款数量
            Integer settlementNumber = 0;//结账数量
            Integer payEndNumber = 0;//已付款数量;

            BigDecimal guestUnitPrice = BigDecimal.ZERO;//客单价
            BigDecimal actualChargeAmount = BigDecimal.ZERO;//实收金额
            BigDecimal shouldChargeAmount = BigDecimal.ZERO;//应收金额
            for (Product pro : entry.getValue()) {

                if (pro.getVoucher() != null) {
                    actualChargeAmount = actualChargeAmount.add(pro.getVoucher().getActualChargeAmount());
                    shouldChargeAmount = shouldChargeAmount.add(pro.getVoucher().getShouldChargeAmount());
                }
                submitNumber++;
                if (pro.getStatus().equals(ProductStatus.TRAILER))
                    refuseNumber++;
                else if (pro.getStatus().equals(ProductStatus.TWO_AUDIT))
                    twoAuditNumber++;
                else if (pro.getStatus().equals(ProductStatus.PROMOTE))
                    promoteNumber++;
                else if (pro.getStatus().equals(ProductStatus.END_APPROACH))
                    endApproachNumber++;
                else if (pro.getStatus().equals(ProductStatus.END))
                    endNumber++;
                else if (pro.getStatus().equals(ProductStatus.PAY_WAIT))
                    payWaitNumber++;
                else if (pro.getStatus().equals(ProductStatus.PAY_RUN))
                    payRunNumber++;
                else if (pro.getStatus().equals(ProductStatus.PAY_TRAILER))
                    payTrailerNumber++;
                else if (pro.getStatus().equals(ProductStatus.SETTLEMENT))
                    settlementNumber++;
            }
            averageDaily = submitNumber * 100 / products.size();
            if (submitNumber.equals(0))
                submitNumber = 1;
            refuseRate = refuseNumber * 100 / submitNumber;
            if (!payEndNumber.equals(0)) {
                guestUnitPrice = actualChargeAmount.divide(new BigDecimal(payEndNumber), 2, RoundingMode.HALF_UP);
            }

            finance.setEmployee(financeEm);
            finance.setSubmitNumber(submitNumber);
            finance.setAverageDaily(averageDaily);
            finance.setRefuseRate(refuseRate);
            finance.setRefuseNumber(refuseNumber);
            finance.setTwoAuditNumber(twoAuditNumber);
            finance.setPromoteNumber(promoteNumber);
            finance.setEndApproachNumber(endApproachNumber);
            finance.setEndNumber(endNumber);
            finance.setPayWaitNumber(payWaitNumber);
            finance.setPayRunNumber(payRunNumber);
            finance.setPayTrailerNumber(payTrailerNumber);
            finance.setSettlementNumber(settlementNumber);
            finance.setPayEndNumber(payEndNumber);
            finance.setGuestUnitPrice(guestUnitPrice);
            finance.setActualChargeAmount(actualChargeAmount);
            finance.setShouldChargeAmount(shouldChargeAmount);

            finances.add(finance);
        }

        if (filter.getSearchRule() == null || filter.getSearchRule().equals("submitNumber")) {
            Collections.sort(finances, new Comparator<Finance>() {
                @Override
                public int compare(Finance o1, Finance o2) {
                    if (filter.getOrderByAsc()) {
                        return o1.getSubmitNumber().compareTo(o2.getSubmitNumber());
                    } else {
                        return o2.getSubmitNumber().compareTo(o1.getSubmitNumber());
                    }
                }
            });
        } else if (filter.getSearchRule().equals("guestUnitPrice")) {
            Collections.sort(finances, new Comparator<Finance>() {
                @Override
                public int compare(Finance o1, Finance o2) {
                    if (filter.getOrderByAsc()) {
                        return o1.getGuestUnitPrice().compareTo(o2.getGuestUnitPrice());
                    } else {
                        return o2.getGuestUnitPrice().compareTo(o1.getGuestUnitPrice());
                    }
                }
            });
        } else {
            Collections.sort(finances, new Comparator<Finance>() {
                @Override
                public int compare(Finance o1, Finance o2) {
                    if (filter.getOrderByAsc()) {
                        return o1.getActualChargeAmount().compareTo(o2.getActualChargeAmount());
                    } else {
                        return o2.getActualChargeAmount().compareTo(o1.getActualChargeAmount());
                    }
                }
            });
        }

        if (filter.isPaged()) {
            List<Finance> resultFinance = new ArrayList<>();
            PagingData data = filter.getPagingData();
            int total = finances.size() > data.getPageSize() ? data.getPageSize(): finances.size();
            for (int i = 0; i < total; i++) {
                Integer index = (data.getPageNumber() - 1) * data.getPageSize();
                Finance newFinance = finances.get(index + i);
                if (filter.getOrderByAsc()) {
                    newFinance.setRank(index + 1 + i);
                } else {
                    newFinance.setRank(finances.size() - index - i);
                }
                resultFinance.add(newFinance);
            }

            result.setPagingResult(new PagingResult(finances.size(), data));
            result.setResult(resultFinance);
        }else {
            result.setResult(finances);
        }

        return result;
    }

    @Override
    public Finance getFinanceByEmployeeId(Integer employeeId) {
        return financeMapper.selectFinanceByEmployeeId(employeeId);
    }

    @Override
    public int getCurrentFinanceRank(Integer employeeId) {
        return financeMapper.selectCurrentFinanceRank(employeeId) + 1;
    }

    @Override
    public void modifyFinance(Finance finance) {
        financeMapper.updateFinance(finance);
    }

    @Override
    public void modifyFinance(Integer employeeId, ProductStatus fromStatus, ProductStatus toStatus) {
        Finance finance = getFinanceByEmployeeId(employeeId);
        setStatusNumber(finance, fromStatus, toStatus);

        financeMapper.updateFinance(finance);
    }

    @Override
    public void createFinance(Finance finance) {
        financeMapper.insertFinance(finance);
    }

    private void setStatusNumber(Finance finance, ProductStatus fromStatus, ProductStatus toStatus) {
        if (fromStatus != null) {
            if (ProductStatus.TRAILER == fromStatus) {
                finance.setRefuseNumber(finance.getRefuseNumber() - 1);
            } else if (ProductStatus.TWO_AUDIT == fromStatus) {
                finance.setTwoAuditNumber(finance.getTwoAuditNumber() - 1);
            } else if (ProductStatus.PROMOTE == fromStatus) {
                finance.setPromoteNumber(finance.getPromoteNumber() - 1);
            } else if (ProductStatus.END_APPROACH == fromStatus) {
                finance.setEndApproachNumber(finance.getEndApproachNumber() - 1);
            } else if (ProductStatus.END == fromStatus) {
                finance.setEndNumber(finance.getEndNumber() - 1);
            } else if (ProductStatus.PAY_WAIT == fromStatus) {
                finance.setPayWaitNumber(finance.getPayWaitNumber() - 1);
            } else if (ProductStatus.PAY_RUN == fromStatus) {
                finance.setPayRunNumber(finance.getPayRunNumber() - 1);
            } else if (ProductStatus.PAY_TRAILER == fromStatus) {
                finance.setPayTrailerNumber(finance.getPayTrailerNumber() - 1);
            } else if (ProductStatus.PAY_END == fromStatus) {
                finance.setPayEndNumber(finance.getPayEndNumber() - 1);
            } else if (ProductStatus.SETTLEMENT == fromStatus) {
                finance.setSettlementNumber(finance.getSettlementNumber() - 1);
            }
        }

        if (toStatus != null) {
            if (ProductStatus.TRAILER == toStatus) {
                finance.setRefuseNumber(finance.getRefuseNumber() + 1);
            } else if (ProductStatus.TWO_AUDIT == toStatus) {
                finance.setTwoAuditNumber(finance.getTwoAuditNumber() + 1);
            } else if (ProductStatus.PROMOTE == toStatus) {
                finance.setPromoteNumber(finance.getPromoteNumber() + 1);
            } else if (ProductStatus.END_APPROACH == toStatus) {
                finance.setEndApproachNumber(finance.getEndApproachNumber() + 1);
            } else if (ProductStatus.END == toStatus) {
                finance.setEndNumber(finance.getEndNumber() + 1);
            } else if (ProductStatus.PAY_WAIT == toStatus) {
                finance.setPayWaitNumber(finance.getPayWaitNumber() + 1);
            } else if (ProductStatus.PAY_RUN == toStatus) {
                finance.setPayRunNumber(finance.getPayRunNumber() + 1);
            } else if (ProductStatus.PAY_TRAILER == toStatus) {
                finance.setPayTrailerNumber(finance.getPayTrailerNumber() + 1);
            } else if (ProductStatus.PAY_END == toStatus) {
                finance.setPayEndNumber(finance.getPayEndNumber() + 1);
            } else if (ProductStatus.SETTLEMENT == toStatus) {
                finance.setSettlementNumber(finance.getSettlementNumber() + 1);
            }
        }

        finance.setRefuseRate(finance.getRefuseNumber() * 100 / finance.getSubmitNumber());
    }
}
