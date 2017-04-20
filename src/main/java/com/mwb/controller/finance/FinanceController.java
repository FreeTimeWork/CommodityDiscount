package com.mwb.controller.finance;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.finance.api.FinanceExcelUtils;
import com.mwb.controller.finance.api.FinanceVO;
import com.mwb.controller.finance.api.SearchFinanceRequest;
import com.mwb.controller.finance.api.SearchFinanceResponse;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.filter.TimeRange;
import com.mwb.dao.model.comm.Log;
import com.mwb.dao.model.comm.PagingData;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.EmployeeStatus;
import com.mwb.dao.model.finance.Finance;
import com.mwb.service.finance.api.IFinanceService;
import com.mwb.util.DateTimeUtility;
import com.mwb.util.ExcelUtility;
import com.mwb.util.FileUtility;
import com.mwb.util.ZipUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MengWeiBo on 2017-04-01
 */

@Controller
@RequestMapping("/finance")
public class FinanceController {
    private static final Log LOG = Log.getLog(FinanceController.class);

    @Autowired
    private IFinanceService financeService;

    @ResponseBody
    @RequestMapping(value = "/search")
    public ServiceResponse search(SearchFinanceRequest request, HttpServletResponse httpResponse, boolean excel) throws IOException, ParseException {
        SearchFinanceResponse searchFinanceResponse = new SearchFinanceResponse();
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return searchFinanceResponse;
        }

        FinanceFilter filter = new FinanceFilter();
        filter.setEmployeeId(request.getEmployeeId());
        filter.setGroupId(request.getGroupId());
        filter.setOrderByAsc(request.getOrderByAsc() == null ? true : request.getOrderByAsc());
        filter.setSearchRule(request.getConditionId());
        filter.setPaged(!excel);

        filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
        filter.setPayDate(TimeRange.toTimeRange(request.getBeginPayTime(), request.getEndPayTime()));
        filter.setSubmitDate(TimeRange.toTimeRange(request.getBeginSubmitTime(), request.getEndSubmitTime()));

        SearchResult<Finance> result = financeService.searchFinance(filter, employee);
        List<FinanceVO> vos = FinanceVO.toVOs(result.getResult());

        searchFinanceResponse.setFinances(vos);
        searchFinanceResponse.setPagingResult(result.getPagingResult());

        //生成excel
        if (excel) {
            HttpServletRequest httpRequest = ApplicationContextUtils.getRequest();
            FileInputStream in = null;
            OutputStream out = null;
            FileOutputStream outStream = null;
            String financeExcelFlooderPath = "/tmp/finance/财务报表/";
            String zipFlooderPath = "/tmp/finance/";
            financeExcelFlooderPath = financeExcelFlooderPath.replace(File.separator, "/");
            zipFlooderPath = zipFlooderPath.replace(File.separator, "/");
            String finance = "/tmp/";
            finance = finance.replace(File.separator, "/");

            File flooder = new File(financeExcelFlooderPath);
            if (!flooder.exists()) {
                flooder.mkdirs();
            }

            try{

                HSSFWorkbook workbook = null;
                File excelFile = null;

                if(CollectionUtils.isEmpty(vos)){
                    return searchFinanceResponse;
                }
                workbook = new HSSFWorkbook();
                //create excel
                FinanceExcelUtils.createFinanceExcel(workbook, vos);
                excelFile = new File(financeExcelFlooderPath +"财务报表.xls");
                outStream = new FileOutputStream(excelFile);
                workbook.write(outStream);
                outStream.flush();
                outStream.close();

                ZipUtility.zip(finance + "finance.zip", "", zipFlooderPath);

                String zipFileName = DateTimeUtility.formatYYYYMMDDHHMMSS(new Date())+ "财务报表";
                zipFileName = ExcelUtility.encodeFileName(zipFileName, httpRequest);

                httpResponse.setContentType("application/x-download");
                httpResponse.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + zipFileName + ".zip");

                in = new FileInputStream(finance + "finance.zip");
                out = httpResponse.getOutputStream();
                byte [] buffer = new byte [1024];
                int length = 0;
                while ((length = in.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, length);
                }

            }catch(Exception e){
                LOG.error("download txt faild! : {}", e);
            }finally{
                in.close();
                out.flush();
                out.close();
                FileUtility.deleteTmpFile(new File(finance));
            }
        }



        return searchFinanceResponse;
    }




}
