package com.ruoyi.common.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.metadata.FieldCache;
import com.alibaba.excel.metadata.FieldWrapper;
import com.alibaba.excel.util.ClassUtils;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.core.service.DictService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StreamUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

import java.lang.reflect.Field;
import java.util.*;

/**
 * <h1>Excel</h1>
 * Excel,1000
 * <p>
 * 1000,,
 *
 * @author Emil.Zhang
 */
@Slf4j
public class ExcelDownHandler implements SheetWriteHandler {

    /**
     * Excel
     * ,
     */
    private static final String EXCEL_COLUMN_NAME = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * Sheet
     */
    private static final String OPTIONS_SHEET_NAME = "options";
    /**
     * Sheet
     */
    private static final String LINKED_OPTIONS_SHEET_NAME = "linkedOptions";
    /**
     *
     */
    private final List<DropDownOptions> dropDownOptions;
    /**
     *
     */
    private int currentOptionsColumnIndex;
    /**
     *
     */
    private int currentLinkedOptionsSheetIndex;
    private final DictService dictService;

    public ExcelDownHandler(List<DropDownOptions> options) {
        this.dropDownOptions = options;
        this.currentOptionsColumnIndex = 0;
        this.currentLinkedOptionsSheetIndex = 0;
        this.dictService = SpringUtils.getBean(DictService.class);
    }

    /**
     * <h2></h2>
     * 1.@ExcelProperty@DropDown
     * value,
     * <p>
     * 2.ExcelUtil,
     * <p>
     * 3.,
     */
    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        //  HSSFWorkbook
        DataValidationHelper helper = sheet.getDataValidationHelper();
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        FieldCache fieldCache = ClassUtils.declaredFields(writeWorkbookHolder.getClazz(), writeWorkbookHolder);
        for (Map.Entry<Integer, FieldWrapper> entry : fieldCache.getSortedFieldMap().entrySet()) {
            Integer index = entry.getKey();
            FieldWrapper wrapper = entry.getValue();
            Field field = wrapper.getField();
            //
            //
            List<String> options = new ArrayList<>();
            if (field.isAnnotationPresent(ExcelDictFormat.class)) {
                // @ExcelDictFormat,
                ExcelDictFormat format = field.getDeclaredAnnotation(ExcelDictFormat.class);
                String dictType = format.dictType();
                String converterExp = format.readConverterExp();
                if (StrUtil.isNotBlank(dictType)) {
                    // ,
                    Collection<String> values = Optional.ofNullable(dictService.getAllDictByDictType(dictType))
                        .orElseThrow(() -> new ServiceException(String.format(" %s ", dictType)))
                        .values();
                    options = new ArrayList<>(values);
                } else if (StrUtil.isNotBlank(converterExp)) {
                    // ,
                    options = StrUtil.split(converterExp, format.separator(), true, true);
                }
            } else if (field.isAnnotationPresent(ExcelEnumFormat.class)) {
                // @ExcelEnumFormat,
                ExcelEnumFormat format = field.getDeclaredAnnotation(ExcelEnumFormat.class);
                List<Object> values = EnumUtil.getFieldValues(format.enumClass(), format.textField());
                options = StreamUtils.toList(values, String::valueOf);
            }
            if (ObjectUtil.isNotEmpty(options)) {
                //
                if (options.size() > 20) {
                    // 20,
                    dropDownWithSheet(helper, workbook, sheet, index, options);
                } else {
                    //
                    dropDownWithSimple(helper, sheet, index, options);
                }
            }
        }
        if (CollUtil.isEmpty(dropDownOptions)) {
            return;
        }
        dropDownOptions.forEach(everyOptions -> {
            //
            if (!everyOptions.getNextOptions().isEmpty()) {
                // ,
                dropDownLinkedOptions(helper, workbook, sheet, everyOptions);
            } else if (everyOptions.getOptions().size() > 10) {
                // 10,
                dropDownWithSheet(helper, workbook, sheet, everyOptions.getIndex(), everyOptions.getOptions());
            } else if (everyOptions.getOptions().size() != 0) {
                // ,
                dropDownWithSimple(helper, sheet, everyOptions.getIndex(), everyOptions.getOptions());
            }
        });
    }

    /**
     * <h2></h2>
     *
     *
     * @param celIndex index
     * @param value
     */
    private void dropDownWithSimple(DataValidationHelper helper, Sheet sheet, Integer celIndex, List<String> value) {
        if (ObjectUtil.isEmpty(value)) {
            return;
        }
        this.markOptionsToSheet(helper, sheet, celIndex, helper.createExplicitListConstraint(ArrayUtil.toArray(value, String.class)));
    }

    /**
     * <h2></h2>
     *
     * @param options
     */
    private void dropDownLinkedOptions(DataValidationHelper helper, Workbook workbook, Sheet sheet, DropDownOptions options) {
        String linkedOptionsSheetName = String.format("%s_%d", LINKED_OPTIONS_SHEET_NAME, currentLinkedOptionsSheetIndex);
        //
        Sheet linkedOptionsDataSheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(linkedOptionsSheetName));
        //
        workbook.setSheetHidden(workbook.getSheetIndex(linkedOptionsDataSheet), true);
        //
        List<String> firstOptions = options.getOptions();
        Map<String, List<String>> secoundOptionsMap = options.getNextOptions();

        //
        Name name = workbook.createName();
        //
        name.setNameName(linkedOptionsSheetName);
        //
        String firstOptionsFunction = String.format("%s!$%s$1:$%s$1",
            linkedOptionsSheetName,
            getExcelColumnName(0),
            getExcelColumnName(firstOptions.size())
        );
        //
        name.setRefersToFormula(firstOptionsFunction);
        // ,
        this.markOptionsToSheet(helper, sheet, options.getIndex(), helper.createFormulaListConstraint(linkedOptionsSheetName));

        for (int columIndex = 0; columIndex < firstOptions.size(); columIndex++) {
            //
            String firstOptionsColumnName = getExcelColumnName(columIndex);
            //
            int finalI = columIndex;
            //
            String thisFirstOptionsValue = firstOptions.get(columIndex);
            //
            Optional.ofNullable(linkedOptionsDataSheet.getRow(0))
                //
                .orElseGet(() -> linkedOptionsDataSheet.createRow(finalI))
                //
                .createCell(columIndex)
                //
                .setCellValue(thisFirstOptionsValue);

            // ,
            List<String> secondOptions = secoundOptionsMap.get(thisFirstOptionsValue);
            if (CollUtil.isEmpty(secondOptions)) {
                // ,Excel
                secondOptions = Collections.singletonList("_0");
            }

            //
            Name sonName = workbook.createName();
            //
            sonName.setNameName(thisFirstOptionsValue);
            //
            String sonFunction = String.format("%s!$%s$2:$%s$%d",
                linkedOptionsSheetName,
                firstOptionsColumnName,
                firstOptionsColumnName,
                secondOptions.size() + 1
            );
            //
            sonName.setRefersToFormula(sonFunction);
            // ,
            // ,Excel
            String mainSheetFirstOptionsColumnName = getExcelColumnName(options.getIndex());
            for (int i = 0; i < 100; i++) {
                //
                String secondOptionsFunction = String.format("=INDIRECT(%s%d)", mainSheetFirstOptionsColumnName, i + 1);
                //
                markLinkedOptionsToSheet(helper, sheet, i, options.getNextIndex(), helper.createFormulaListConstraint(secondOptionsFunction));
            }

            for (int rowIndex = 0; rowIndex < secondOptions.size(); rowIndex++) {
                //
                int finalRowIndex = rowIndex + 1;
                int finalColumIndex = columIndex;

                Row row = Optional.ofNullable(linkedOptionsDataSheet.getRow(finalRowIndex))
                    //
                    .orElseGet(() -> linkedOptionsDataSheet.createRow(finalRowIndex));
                Optional
                    //
                    .ofNullable(row.getCell(finalColumIndex))
                    //
                    .orElseGet(() -> row.createCell(finalColumIndex))
                    //
                    .setCellValue(secondOptions.get(rowIndex));
            }
        }

        currentLinkedOptionsSheetIndex++;
    }

    /**
     * <h2></h2>
     * ,Excel,
     *
     * @param celIndex
     * @param value
     */
    private void dropDownWithSheet(DataValidationHelper helper, Workbook workbook, Sheet sheet, Integer celIndex, List<String> value) {
        //
        Sheet simpleDataSheet = Optional.ofNullable(workbook.getSheet(WorkbookUtil.createSafeSheetName(OPTIONS_SHEET_NAME)))
            .orElseGet(() -> workbook.createSheet(WorkbookUtil.createSafeSheetName(OPTIONS_SHEET_NAME)));
        //
        workbook.setSheetHidden(workbook.getSheetIndex(simpleDataSheet), true);
        //
        for (int i = 0; i < value.size(); i++) {
            int finalI = i;
            // ,
            Row row = Optional.ofNullable(simpleDataSheet.getRow(i))
                .orElseGet(() -> simpleDataSheet.createRow(finalI));
            // ,
            Cell cell = Optional.ofNullable(row.getCell(currentOptionsColumnIndex))
                .orElseGet(() -> row.createCell(currentOptionsColumnIndex));
            //
            cell.setCellValue(value.get(i));
        }

        //
        Name name = workbook.createName();
        //
        String nameName = String.format("%s_%d", OPTIONS_SHEET_NAME, celIndex);
        name.setNameName(nameName);
        //
        String function = String.format("%s!$%s$1:$%s$%d",
            OPTIONS_SHEET_NAME,
            getExcelColumnName(currentOptionsColumnIndex),
            getExcelColumnName(currentOptionsColumnIndex),
            value.size());
        //
        name.setRefersToFormula(function);
        // ,
        this.markOptionsToSheet(helper, sheet, celIndex, helper.createFormulaListConstraint(nameName));
        currentOptionsColumnIndex++;
    }

    /**
     * ,
     */
    private void markOptionsToSheet(DataValidationHelper helper, Sheet sheet, Integer celIndex,
                                    DataValidationConstraint constraint) {
        // ,:、、、
        CellRangeAddressList addressList = new CellRangeAddressList(1, 1000, celIndex, celIndex);
        markDataValidationToSheet(helper, sheet, constraint, addressList);
    }

    /**
     * ,
     */
    private void markLinkedOptionsToSheet(DataValidationHelper helper, Sheet sheet, Integer rowIndex,
                                          Integer celIndex, DataValidationConstraint constraint) {
        // ,:、、、
        CellRangeAddressList addressList = new CellRangeAddressList(rowIndex, rowIndex, celIndex, celIndex);
        markDataValidationToSheet(helper, sheet, constraint, addressList);
    }

    /**
     *
     */
    private void markDataValidationToSheet(DataValidationHelper helper, Sheet sheet,
                                           DataValidationConstraint constraint, CellRangeAddressList addressList) {
        //
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        // Excel
        if (dataValidation instanceof XSSFDataValidation) {
            //
            dataValidation.setSuppressDropDownArrow(true);
            //
            dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
            dataValidation.createErrorBox("", "");
            dataValidation.setShowErrorBox(true);
            //
            dataValidation.createPromptBox(":", ",");
            dataValidation.setShowPromptBox(true);
            sheet.addValidationData(dataValidation);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(dataValidation);
    }

    /**
     * <h2>index</h2>
     * indexExcel
     * <p>1,index0,A</p>
     * 27,index26,AA
     * <p>28,index27,AB</p>
     *
     * @param columnIndex index
     * @return index
     */
    private String getExcelColumnName(int columnIndex) {
        // 26
        int columnCircleCount = columnIndex / 26;
        // 26
        int thisCircleColumnIndex = columnIndex % 26;
        // 260,
        String columnPrefix = columnCircleCount == 0
            ? StrUtil.EMPTY
            : StrUtil.subWithLength(EXCEL_COLUMN_NAME, columnCircleCount - 1, 1);
        // 26
        String columnNext = StrUtil.subWithLength(EXCEL_COLUMN_NAME, thisCircleColumnIndex, 1);
        //
        return columnPrefix + columnNext;
    }
}
