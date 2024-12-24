package com.ruoyi.common.convert;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.utils.reflect.ReflectUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author Liang
 */
@Slf4j
public class ExcelEnumConvert implements Converter<Object> {

    @Override
    public Class<Object> supportJavaTypeKey() {
        return Object.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public Object convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        cellData.checkEmpty();
        // Excel
        Object textValue = null;
        switch (cellData.getType()) {
            case STRING:
            case DIRECT_STRING:
            case RICH_TEXT_STRING:
                textValue = cellData.getStringValue();
                break;
            case NUMBER:
                textValue = cellData.getNumberValue();
                break;
            case BOOLEAN:
                textValue = cellData.getBooleanValue();
                break;
            default:
                throw new IllegalArgumentException("!");
        }
        //
        if (ObjectUtil.isNull(textValue)) {
            return null;
        }
        Map<Object, String> enumCodeToTextMap = beforeConvert(contentProperty);
        // JavaExcelcodetext
        // ExcelJavatextcode
        Map<Object, Object> enumTextToCodeMap = new HashMap<>();
        enumCodeToTextMap.forEach((key, value) -> enumTextToCodeMap.put(value, key));
        // text -> code
        Object codeValue = enumTextToCodeMap.get(textValue);
        return Convert.convert(contentProperty.getField().getType(), codeValue);
    }

    @Override
    public WriteCellData<String> convertToExcelData(Object object, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        if (ObjectUtil.isNull(object)) {
            return new WriteCellData<>("");
        }
        Map<Object, String> enumValueMap = beforeConvert(contentProperty);
        String value = Convert.toStr(enumValueMap.get(object), "");
        return new WriteCellData<>(value);
    }

    private Map<Object, String> beforeConvert(ExcelContentProperty contentProperty) {
        ExcelEnumFormat anno = getAnnotation(contentProperty.getField());
        Map<Object, String> enumValueMap = new HashMap<>();
        Enum<?>[] enumConstants = anno.enumClass().getEnumConstants();
        for (Enum<?> enumConstant : enumConstants) {
            Object codeValue = ReflectUtils.invokeGetter(enumConstant, anno.codeField());
            String textValue = ReflectUtils.invokeGetter(enumConstant, anno.textField());
            enumValueMap.put(codeValue, textValue);
        }
        return enumValueMap;
    }

    private ExcelEnumFormat getAnnotation(Field field) {
        return AnnotationUtil.getAnnotation(field, ExcelEnumFormat.class);
    }
}
