package com.ruoyi.common.core.domain.vo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class SysUserVo {

    private Long id;

    private String nickname;

    private String avatar;

    private Integer avatarIndex;

    private String walletAddress;

    private String chainType;

    private BigDecimal balance;

    private Long earn;

    private String inviteCode;

    private Long currentRanking;

    private Long inviteEarn;

    private Long inviteTotalCount;

    private Long inviteActivateCount;
}
