package com.ruoyi.web.controller.common;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.CaptchaType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.email.MailUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.reflect.ReflectUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.config.properties.CaptchaProperties;
import com.ruoyi.framework.config.properties.MailProperties;
import com.ruoyi.system.service.ISysConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.dromara.sms4j.provider.enumerate.SupplierType;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 *
 * @author Lion Li
 */
@SaIgnore
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
public class CaptchaController {

    private final CaptchaProperties captchaProperties;
    private final ISysConfigService configService;
    private final MailProperties mailProperties;

    /**
     *
     *
     * @param phonenumber
     */
    @GetMapping("/captchaSms")
    public R<Void> smsCaptcha(@NotBlank(message = "{user.phonenumber.not.blank}") String phonenumber) {
        String key = CacheConstants.CAPTCHA_CODE_KEY + phonenumber;
        String code = RandomUtil.randomNumbers(4);
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(Constants.CAPTCHA_EXPIRATION));
        // id  ()
        String templateId = "";
        LinkedHashMap<String, String> map = new LinkedHashMap<>(1);
        map.put("code", code);
        SmsBlend smsBlend = SmsFactory.createSmsBlend(SupplierType.ALIBABA);
        SmsResponse smsResponse = smsBlend.sendMessage(phonenumber, templateId, map);
        if (!"OK".equals(smsResponse.getCode())) {
            log.error(" => {}", smsResponse);
            return R.fail(smsResponse.getMessage());
        }
        return R.ok();
    }

    /**
     *
     *
     * @param email
     */
    @GetMapping("/captchaEmail")
    public R<Void> emailCode(@NotBlank(message = "{user.email.not.blank}") String email) {
        if (!mailProperties.getEnabled()) {
            return R.fail("！");
        }
        String key = CacheConstants.CAPTCHA_CODE_KEY + email;
        String code = RandomUtil.randomNumbers(4);
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(Constants.CAPTCHA_EXPIRATION));
        try {
            MailUtils.sendText(email, "", "：" + code + "，" + Constants.CAPTCHA_EXPIRATION + "，。");
        } catch (Exception e) {
            log.error(" => {}", e.getMessage());
            return R.fail(e.getMessage());
        }
        return R.ok();
    }

    /**
     *
     */
    @GetMapping("/captchaImage")
    public R<Map<String, Object>> getCode() {
        Map<String, Object> ajax = new HashMap<>();
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return R.ok(ajax);
        }
        //
        String uuid = IdUtil.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
        //
        CaptchaType captchaType = captchaProperties.getType();
        boolean isMath = CaptchaType.MATH == captchaType;
        Integer length = isMath ? captchaProperties.getNumberLength() : captchaProperties.getCharLength();
        CodeGenerator codeGenerator = ReflectUtils.newInstance(captchaType.getClazz(), length);
        AbstractCaptcha captcha = SpringUtils.getBean(captchaProperties.getCategory().getClazz());
        captcha.setGenerator(codeGenerator);
        captcha.createCode();
        String code = captcha.getCode();
        if (isMath) {
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(StringUtils.remove(code, "="));
            code = exp.getValue(String.class);
        }
        RedisUtils.setCacheObject(verifyKey, code, Duration.ofMinutes(Constants.CAPTCHA_EXPIRATION));
        ajax.put("uuid", uuid);
        ajax.put("img", captcha.getImageBase64());
        return R.ok(ajax);
    }

}
