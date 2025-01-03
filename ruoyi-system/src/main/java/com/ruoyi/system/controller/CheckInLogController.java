package com.ruoyi.system.controller;

import java.util.List;
import java.util.Arrays;

import cn.hutool.core.util.ObjUtil;
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
import com.ruoyi.system.domain.vo.CheckInLogVo;
import com.ruoyi.system.domain.bo.CheckInLogBo;
import com.ruoyi.system.service.ICheckInLogService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * check in
 *
 * @author ruoyi
 * @date 2024-12-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/checkInLog")
public class CheckInLogController extends BaseController {

    private final ICheckInLogService iCheckInLogService;

    @PostMapping("/checkIn")
    public R<Void> checkIn(@RequestBody CheckInLogBo bo) {
        CheckInLogVo checkInLogVo = iCheckInLogService.queryByHash(bo.getHash());
        if (ObjUtil.isNotNull(checkInLogVo)) {
            return R.fail("The hash value has been used and cannot be reused");
        }
        if (iCheckInLogService.checkIn(bo)) {
            return R.ok("Sign in successfully");
        } else {
            return R.fail("Sign in failed");
        }
    }

    /**
     * list
     */
    @SaCheckPermission("system:checkInLog:list")
    @GetMapping("/list")
    public TableDataInfo<CheckInLogVo> list(CheckInLogBo bo, PageQuery pageQuery) {
        return iCheckInLogService.queryPageList(bo, pageQuery);
    }

    /**
     * export
     */
    @SaCheckPermission("system:checkInLog:export")
    @Log(title = "", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CheckInLogBo bo, HttpServletResponse response) {
        List<CheckInLogVo> list = iCheckInLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "", CheckInLogVo.class, response);
    }

    /**
     * info
     *
     * @param id id
     */
    @SaCheckPermission("system:checkInLog:query")
    @GetMapping("/{id}")
    public R<CheckInLogVo> getInfo(@NotNull(message = "id is null")
                                     @PathVariable Long id) {
        return R.ok(iCheckInLogService.queryById(id));
    }

    /**
     * add
     */
    @SaCheckPermission("system:checkInLog:add")
    @Log(title = "", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CheckInLogBo bo) {
        return toAjax(iCheckInLogService.insertByBo(bo));
    }

    /**
     * edit
     */
    @SaCheckPermission("system:checkInLog:edit")
    @Log(title = "", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CheckInLogBo bo) {
        return toAjax(iCheckInLogService.updateByBo(bo));
    }

    /**
     * remove
     *
     * @param ids ids
     */
    @SaCheckPermission("system:checkInLog:remove")
    @Log(title = "", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "ids is empty")
                          @PathVariable Long[] ids) {
        return toAjax(iCheckInLogService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
