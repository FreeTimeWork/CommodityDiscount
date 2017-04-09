package com.mwb.controller.finance.api;

import java.util.List;

import com.mwb.dao.model.comm.Bool;
import com.mwb.dao.model.comm.CrudType;
import com.mwb.util.ExcelUtility;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Created by Fangchen.chai on 2017/4/7.
 */
public class FinanceExcelUtils {



    public static void createFinanceExcel(HSSFWorkbook workbook, List<FinanceVO> vos){

        HSSFSheet sheet = ExcelUtility.createSheet(workbook, FinanceExcelConstants.SHEET_TITLE);

        CellStyle titleCellStyle = ExcelUtility.getCellStyle(workbook, true);
        int rowIndex = 0;
        int columnIndex = 0;

        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_RANK, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_EMPLOYEE, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_SUBMIT_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_AVERAGE_DAILY, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_REFUSE_RATE, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_REFUSE_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_TWO_AUDIT_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_PROMOTE_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_END_APPROACH_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_END_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_PAY_WAIT_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_PAY_RUN_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_PAY_TRAILER_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_PAY_END_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_SETTLEMENT_NUM, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_GUEST_UNIT_PRICE, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_ACTUAL_CHARGE_AMOUNT, titleCellStyle);
        ExcelUtility.createCell(sheet, rowIndex, columnIndex++, FinanceExcelConstants.LABEL_SHOULD_CHARGE_AMOUNT, titleCellStyle);

        rowIndex = 1;
        CellStyle rowCellStyle = ExcelUtility.getCellStyle(workbook, CrudType.UPDATE, Bool.N);

        for (FinanceVO vo : vos) {
            columnIndex = 0;

            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getRanking(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getEmployeeName(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getSubmitNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getAverageDaily(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getRefuseRate(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getRefuseNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getTwoAuditNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getPromoteNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getEndApproachNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getEndNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getPayWaitNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getPayRunNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getPayTrailerNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getPayEndNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getSettlementNumber(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getGuestUnitPrice(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getActualChargeAmount(), rowCellStyle);
            ExcelUtility.createCell(sheet, rowIndex, columnIndex++, vo.getShouldChargeAmount(), rowCellStyle);

            rowIndex ++;
        }

    }
}
