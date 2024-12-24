package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import cn.dev33.satoken.annotation.SaIgnore;
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
import com.ruoyi.system.domain.vo.AiAgentVo;
import com.ruoyi.system.domain.bo.AiAgentBo;
import com.ruoyi.system.service.IAiAgentService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * Ai
 *
 * @author ruoyi
 * @date 2024-12-22
 */
@Validated
@RequiredArgsConstructor
@RestController
public class AiAgentController extends BaseController {

    private final IAiAgentService iAiAgentService;


    /**
     *
     */
    @SaIgnore
    @DeleteMapping("/aiAgent/delAgent/{id}")
    public R<?> delAgent(@PathVariable Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);

        return R.ok(iAiAgentService.deleteWithValidByIds(ids, true));
    }


    /**
     * Ai
     */
    @SaIgnore
    @GetMapping("/aiAgent/getMyAgentList")
    public R<?> getMyAgentList(AiAgentBo bo) {
        return R.ok(iAiAgentService.queryList(bo));
    }

    /**
     * Ai
     */
    @Log(title = "Ai", businessType = BusinessType.INSERT)
    @SaIgnore
    @PostMapping("/aiAgent/createAgent")
    public R<Void> createAgent(@Validated(AddGroup.class) @RequestBody AiAgentBo bo) {
        bo.setUserId(1864598613468958722L);
        bo.setStatus("1");
        return toAjax(iAiAgentService.insertByBo(bo));
    }

    /**
     * Ai
     */
    @SaCheckPermission("system:aiAgent:list")
    @GetMapping("/system/aiAgent/list")
    public TableDataInfo<AiAgentVo> list(AiAgentBo bo, PageQuery pageQuery) {
        return iAiAgentService.queryPageList(bo, pageQuery);
    }

    /**
     * Ai
     */
    @SaCheckPermission("system:aiAgent:export")
    @Log(title = "Ai", businessType = BusinessType.EXPORT)
    @PostMapping("/system/aiAgent/export")
    public void export(AiAgentBo bo, HttpServletResponse response) {
        List<AiAgentVo> list = iAiAgentService.queryList(bo);
        ExcelUtil.exportExcel(list, "Ai", AiAgentVo.class, response);
    }

    /**
     * Ai
     *
     * @param id
     */
    @SaCheckPermission("system:aiAgent:query")
    @GetMapping("/system/aiAgent/{id}")
    public R<AiAgentVo> getInfo(@NotNull(message = "")
                                     @PathVariable Long id) {
        return R.ok(iAiAgentService.queryById(id));
    }

    /**
     * Ai
     */
    @SaCheckPermission("system:aiAgent:add")
    @Log(title = "Ai", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/system/aiAgent")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiAgentBo bo) {
        return toAjax(iAiAgentService.insertByBo(bo));
    }

    /**
     * Ai
     */
    @SaCheckPermission("system:aiAgent:edit")
    @Log(title = "Ai", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/system/aiAgent")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiAgentBo bo) {
        return toAjax(iAiAgentService.updateByBo(bo));
    }

    /**
     * Ai
     *
     * @param ids
     */
    @SaCheckPermission("system:aiAgent:remove")
    @Log(title = "Ai", businessType = BusinessType.DELETE)
    @DeleteMapping("/system/aiAgent/{ids}")
    public R<Void> remove(@NotEmpty(message = "")
                          @PathVariable Long[] ids) {
        return toAjax(iAiAgentService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
