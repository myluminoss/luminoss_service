package com.ruoyi.system.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.lock.annotation.Lock4j;
import com.ruoyi.system.domain.dto.LoginByWalletRequest;

import com.ruoyi.system.service.SysLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessType;

import java.util.Map;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final SysLoginService loginService;

    /**
     * login by wallet
     */
    @Log(title = "", businessType = BusinessType.INSERT)
    @PostMapping("/loginByWallet")
    @SaIgnore
    @Lock4j(name = "loginByWallet", keys = {"#request.walletAddress"}, expire = 6000)
    public R<?> loginByWallet(@Validated @RequestBody LoginByWalletRequest request) throws InterruptedException {
        Map<String, Object> res = loginService.walletLogin(request);
        return R.ok(res);
    }

}
