package com.ruoyi.system.domain.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginByWalletRequest {


    @NotBlank(message = "")
    private String walletAddress;

    @NotBlank(message = "")
    private String walletSignature;

    private String fullMessage;

    private String publicKey;

    private String chainType;
}
