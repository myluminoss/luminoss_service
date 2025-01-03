package com.ruoyi.system.controller;

import java.util.List;
import java.util.Arrays;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;

import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.vo.AiTransactionLogVo;
import com.ruoyi.system.domain.bo.AiTransactionLogBo;
import com.ruoyi.system.service.IAiTransactionLogService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * AiTransactionLog
 *
 * @author ruoyi
 * @date 2024-12-27
 */
@Validated
@RequiredArgsConstructor
@RestController
public class AiTransactionLogController extends BaseController {

    private final IAiTransactionLogService iAiTransactionLogService;

    /**
     *
     */
    @SaIgnore
    @GetMapping("/aiTransactionLog/list")
    public TableDataInfo<AiTransactionLogVo> aiTransactionLog(AiTransactionLogBo bo, PageQuery pageQuery) {
        if (ObjUtil.isNull(bo.getAiAgentId())) {
            throw new ServiceException("Missing ai agent id");
        }
        return iAiTransactionLogService.queryPageList(bo, pageQuery);
    }

    /**
     *
     */
    @SaCheckPermission("system:aiTransactionLog:list")
    @GetMapping("/system/aiTransactionLog/list")
    public TableDataInfo<AiTransactionLogVo> list(AiTransactionLogBo bo, PageQuery pageQuery) {
        return iAiTransactionLogService.queryPageList(bo, pageQuery);
    }

    /**
     *
     */
    @SaCheckPermission("system:aiTransactionLog:export")
    @Log(title = "AiTransactionLog", businessType = BusinessType.EXPORT)
    @PostMapping("/system/aiTransactionLog/export")
    public void export(AiTransactionLogBo bo, HttpServletResponse response) {
        List<AiTransactionLogVo> list = iAiTransactionLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "AiTransactionLog", AiTransactionLogVo.class, response);
    }

    /**
     *
     *
     * @param id
     */
    @SaCheckPermission("system:aiTransactionLog:query")
    @GetMapping("/system/aiTransactionLog/{id}")
    public R<AiTransactionLogVo> getInfo(@NotNull(message = "The primary key cannot be empty")
                                     @PathVariable Long id) {
        return R.ok(iAiTransactionLogService.queryById(id));
    }

    /**
     * AiTransactionLog
     */
    @SaCheckPermission("system:aiTransactionLog:add")
    @Log(title = "AiTransactionLog", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/system/aiTransactionLog")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiTransactionLogBo bo) {
        return toAjax(iAiTransactionLogService.insertByBo(bo));
    }

    /**
     * AiTransactionLog
     */
    @SaCheckPermission("system:aiTransactionLog:edit")
    @Log(title = "AiTransactionLog", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/system/aiTransactionLog")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiTransactionLogBo bo) {
        return toAjax(iAiTransactionLogService.updateByBo(bo));
    }

    /**
     * AiTransactionLog
     *
     * @param ids
     */
    @SaCheckPermission("system:aiTransactionLog:remove")
    @Log(title = "AiTransactionLog", businessType = BusinessType.DELETE)
    @DeleteMapping("/system/aiTransactionLog/{ids}")
    public R<Void> remove(@NotEmpty(message = "The primary key cannot be empty")
                          @PathVariable Long[] ids) {
        return toAjax(iAiTransactionLogService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
