package com.ruoyi.system.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.vo.UserIntegralLogVo;
import com.ruoyi.system.domain.bo.UserIntegralLogBo;
import com.ruoyi.system.service.IUserIntegralLogService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * user integral log
 *
 * @author ruoyi
 * @date 2024-12-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/integralLog")
public class UserIntegralLogController extends BaseController {

    private final IUserIntegralLogService iUserIntegralLogService;

    /**
     *
     */
    @SaCheckPermission("system:integralLog:list")
    @GetMapping("/list")
    public TableDataInfo<UserIntegralLogVo> list(UserIntegralLogBo bo, PageQuery pageQuery) {
        return iUserIntegralLogService.queryPageList(bo, pageQuery);
    }

    /**
     *
     */
    @SaCheckPermission("system:integralLog:export")
    @Log(title = "user integral log", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(UserIntegralLogBo bo, HttpServletResponse response) {
        List<UserIntegralLogVo> list = iUserIntegralLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "user integral log", UserIntegralLogVo.class, response);
    }

    /**
     *
     *
     * @param id
     */
    @SaCheckPermission("system:integralLog:query")
    @GetMapping("/{id}")
    public R<UserIntegralLogVo> getInfo(@NotNull(message = "The primary key cannot be empty")
                                     @PathVariable Long id) {
        return R.ok(iUserIntegralLogService.queryById(id));
    }

    /**
     * user integral log
     */
    @SaCheckPermission("system:integralLog:add")
    @Log(title = "user integral log", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody UserIntegralLogBo bo) {
        return toAjax(iUserIntegralLogService.insertByBo(bo));
    }

    /**
     * user integral log
     */
    @SaCheckPermission("system:integralLog:edit")
    @Log(title = "user integral log", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody UserIntegralLogBo bo) {
        return toAjax(iUserIntegralLogService.updateByBo(bo));
    }

    /**
     * user integral log
     *
     * @param ids
     */
    @SaCheckPermission("system:integralLog:remove")
    @Log(title = "user integral log", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "The primary key cannot be empty")
                          @PathVariable Long[] ids) {
        return toAjax(iUserIntegralLogService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
