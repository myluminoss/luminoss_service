package com.ruoyi.common.excel;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Emil.Zhang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class DropDownOptions {

    /**
     */
    private int index = 0;

    /**
     */
    private int nextIndex = 0;

    /**
     */
    private List<String> options = new ArrayList<>();

    /**
     */
    private Map<String, List<String>> nextOptions = new HashMap<>();

    /**
     */
    private static final String DELIMITER = "_";

    /**
     */
    public DropDownOptions(int index, List<String> options) {
        this.index = index;
        this.options = options;
    }

    public static String createOptionValue(Object... vars) {
        StringBuilder stringBuffer = new StringBuilder();
        String regex = "^[\\S\\d\\u4e00-\\u9fa5]+$";
        for (int i = 0; i < vars.length; i++) {
            String var = StrUtil.trimToEmpty(String.valueOf(vars[i]));
            if (!var.matches(regex)) {
                throw new ServiceException(",");
            }
            stringBuffer.append(var);
            if (i < vars.length - 1) {
                // ,_
                stringBuffer.append(DELIMITER);
            }
        }
        if (stringBuffer.toString().matches("^\\d_*$")) {
            throw new ServiceException("");
        }
        return stringBuffer.toString();
    }

    /**
     *
     *
     * @param option
     * @return
     */
    public static List<String> analyzeOptionValue(String option) {
        return StrUtil.split(option, DELIMITER, true, true);
    }

    /**
     *
     *
     * @param parentList
     * @param parentIndex
     * @param sonList
     * @param sonIndex
     * @param parentHowToGetIdFunction
     * @param sonHowToGetParentIdFunction
     * @param howToBuildEveryOption
     * @return
     */
    public static <T> DropDownOptions buildLinkedOptions(List<T> parentList,
                                                         int parentIndex,
                                                         List<T> sonList,
                                                         int sonIndex,
                                                         Function<T, Number> parentHowToGetIdFunction,
                                                         Function<T, Number> sonHowToGetParentIdFunction,
                                                         Function<T, String> howToBuildEveryOption) {
        DropDownOptions parentLinkSonOptions = new DropDownOptions();
        //
        parentLinkSonOptions.setIndex(parentIndex);
        parentLinkSonOptions.setOptions(
            parentList.stream()
                .map(howToBuildEveryOption)
                .collect(Collectors.toList())
        );
        // -
        Map<String, List<String>> sonOptions = new HashMap<>();
        // ID
        Map<Number, List<T>> parentGroupByIdMap =
            parentList.stream().collect(Collectors.groupingBy(parentHowToGetIdFunction));
        // ,Map
        sonList.forEach(everySon -> {
            if (parentGroupByIdMap.containsKey(sonHowToGetParentIdFunction.apply(everySon))) {
                //
                T parentObj = parentGroupByIdMap.get(sonHowToGetParentIdFunction.apply(everySon)).get(0);
                // IDKey
                String key = howToBuildEveryOption.apply(parentObj);
                // KeyValue
                List<String> thisParentSonOptionList;
                if (sonOptions.containsKey(key)) {
                    thisParentSonOptionList = sonOptions.get(key);
                } else {
                    thisParentSonOptionList = new ArrayList<>();
                    sonOptions.put(key, thisParentSonOptionList);
                }
                // Value
                thisParentSonOptionList.add(howToBuildEveryOption.apply(everySon));
            }
        });
        parentLinkSonOptions.setNextIndex(sonIndex);
        parentLinkSonOptions.setNextOptions(sonOptions);
        return parentLinkSonOptions;
    }
}
