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
import com.ruoyi.system.domain.vo.UserIntegralVo;
import com.ruoyi.system.domain.bo.UserIntegralBo;
import com.ruoyi.system.service.IUserIntegralService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * user integral
 *
 * @author ruoyi
 * @date 2024-12-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/integral")
public class UserIntegralController extends BaseController {

    private final IUserIntegralService iUserIntegralService;

    /**
     *
     */
    @SaCheckPermission("system:integral:list")
    @GetMapping("/list")
    public TableDataInfo<UserIntegralVo> list(UserIntegralBo bo, PageQuery pageQuery) {
        return iUserIntegralService.queryPageList(bo, pageQuery);
    }

    /**
     *
     */
    @SaCheckPermission("system:integral:export")
    @Log(title = "user integral", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(UserIntegralBo bo, HttpServletResponse response) {
        List<UserIntegralVo> list = iUserIntegralService.queryList(bo);
        ExcelUtil.exportExcel(list, "user integral", UserIntegralVo.class, response);
    }

    /**
     *
     *
     * @param id
     */
    @SaCheckPermission("system:integral:query")
    @GetMapping("/{id}")
    public R<UserIntegralVo> getInfo(@NotNull(message = "The primary key cannot be empty")
                                     @PathVariable Long id) {
        return R.ok(iUserIntegralService.queryById(id));
    }

    /**
     * user integral
     */
    @SaCheckPermission("system:integral:add")
    @Log(title = "user integral", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody UserIntegralBo bo) {
        return toAjax(iUserIntegralService.insertByBo(bo));
    }

    /**
     * user integral
     */
    @SaCheckPermission("system:integral:edit")
    @Log(title = "user integral", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody UserIntegralBo bo) {
        return toAjax(iUserIntegralService.updateByBo(bo));
    }

    /**
     * user integral
     *
     * @param ids
     */
    @SaCheckPermission("system:integral:remove")
    @Log(title = "user integral", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "The primary key cannot be empty")
                          @PathVariable Long[] ids) {
        return toAjax(iUserIntegralService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
