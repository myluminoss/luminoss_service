package com.ruoyi.system.listener;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.excel.ExcelListener;
import com.ruoyi.common.excel.ExcelResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.ValidatorUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.domain.vo.SysUserImportVo;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
@Slf4j
public class SysUserImportListener extends AnalysisEventListener<SysUserImportVo> implements ExcelListener<SysUserImportVo> {

    private final ISysUserService userService;

    private final String password;

    private final Boolean isUpdateSupport;

    private final String operName;

    private int successNum = 0;
    private int failureNum = 0;
    private final StringBuilder successMsg = new StringBuilder();
    private final StringBuilder failureMsg = new StringBuilder();

    public SysUserImportListener(Boolean isUpdateSupport) {
        String initPassword = SpringUtils.getBean(ISysConfigService.class).selectConfigByKey("sys.user.initPassword");
        this.userService = SpringUtils.getBean(ISysUserService.class);
        this.password = BCrypt.hashpw(initPassword);
        this.isUpdateSupport = isUpdateSupport;
        this.operName = LoginHelper.getUsername();
    }

    @Override
    public void invoke(SysUserImportVo userVo, AnalysisContext context) {
        SysUser user = this.userService.selectUserByUserName(userVo.getUserName());
        try {
            //
            if (ObjectUtil.isNull(user)) {
                user = BeanUtil.toBean(userVo, SysUser.class);
                ValidatorUtils.validate(user);
                user.setPassword(password);
                user.setCreateBy(operName);
                userService.insertUser(user);
                successNum++;
                successMsg.append("<br/>").append(successNum).append("、 ").append(user.getUserName()).append(" ");
            } else if (isUpdateSupport) {
                Long userId = user.getUserId();
                user = BeanUtil.toBean(userVo, SysUser.class);
                user.setUserId(userId);
                ValidatorUtils.validate(user);
                userService.checkUserAllowed(user);
                userService.checkUserDataScope(user.getUserId());
                user.setUpdateBy(operName);
                userService.updateUser(user);
                successNum++;
                successMsg.append("<br/>").append(successNum).append("、 ").append(user.getUserName()).append(" ");
            } else {
                failureNum++;
                failureMsg.append("<br/>").append(failureNum).append("、 ").append(user.getUserName()).append(" ");
            }
        } catch (Exception e) {
            failureNum++;
            String msg = "<br/>" + failureNum + "、 " + user.getUserName() + " :";
            failureMsg.append(msg).append(e.getMessage());
            log.error(msg, e);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    @Override
    public ExcelResult<SysUserImportVo> getExcelResult() {
        return new ExcelResult<SysUserImportVo>() {

            @Override
            public String getAnalysis() {
                if (failureNum > 0) {
                    failureMsg.insert(0, ",! " + failureNum + " ,:");
                    throw new ServiceException(failureMsg.toString());
                } else {
                    successMsg.insert(0, ",! " + successNum + " ,:");
                }
                return successMsg.toString();
            }

            @Override
            public List<SysUserImportVo> getList() {
                return null;
            }

            @Override
            public List<String> getErrorList() {
                return null;
            }
        };
    }
}
