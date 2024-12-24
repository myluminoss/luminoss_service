package com.ruoyi.common.captcha;

import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.math.Calculator;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.utils.StringUtils;

public class UnsignedMathGenerator implements CodeGenerator {

    private static final long serialVersionUID = -5514819971774091076L;

    private static final String OPERATORS = "+-*";

    /**
     *
     */
    private final int numberLength;

    /**
     *
     */
    public UnsignedMathGenerator() {
        this(2);
    }

    /**
     *
     *
     * @param numberLength
     */
    public UnsignedMathGenerator(int numberLength) {
        this.numberLength = numberLength;
    }

    @Override
    public String generate() {
        final int limit = getLimit();
        int a = RandomUtil.randomInt(limit);
        int b = RandomUtil.randomInt(limit);
        String max = Integer.toString(Math.max(a,b));
        String min = Integer.toString(Math.min(a,b));
        max = StringUtils.rightPad(max, this.numberLength, CharUtil.SPACE);
        min = StringUtils.rightPad(min, this.numberLength, CharUtil.SPACE);

        return max + RandomUtil.randomChar(OPERATORS) + min + '=';
    }

    @Override
    public boolean verify(String code, String userInputCode) {
        int result;
        try {
            result = Integer.parseInt(userInputCode);
        } catch (NumberFormatException e) {
            //
            return false;
        }

        final int calculateResult = (int) Calculator.conversion(code);
        return result == calculateResult;
    }

    /**
     *
     *
     * @return
     */
    public int getLength() {
        return this.numberLength * 2 + 2;
    }

    /**
     *
     *
     * @return
     */
    private int getLimit() {
        return Integer.parseInt("1" + StringUtils.repeat('0', this.numberLength));
    }
}
