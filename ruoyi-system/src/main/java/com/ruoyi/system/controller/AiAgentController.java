package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.vo.SysOssVo;
import com.ruoyi.system.service.ISysOssService;
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
 * Ai agent
 *
 * @author ruoyi
 * @date 2024-12-22
 */
@Validated
@RequiredArgsConstructor
@RestController
public class AiAgentController extends BaseController {

    private final IAiAgentService iAiAgentService;

    private final ISysOssService iSysOssService;

    /**
     * get Ai agent details
     */
    @SaIgnore
    @GetMapping("/aiAgent/getAgentDetails/{id}")
    public R<?> getAgentDetails(@PathVariable Long id) {

        return R.ok(iAiAgentService.getAgentDetails(id));
    }

    /**
     * get Ai agent list
     */
    @SaIgnore
    @GetMapping("/aiAgent/getAgentList")
    public TableDataInfo<AiAgentVo> getAgentList(AiAgentBo bo, PageQuery pageQuery) {

        return iAiAgentService.getAgentList(bo, pageQuery);
    }

    /**
     * delete Ai
     */
    @DeleteMapping("/aiAgent/delAgent/{id}")
    public R<?> delAgent(@PathVariable Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);

        AiAgentVo aiAgentVo = iAiAgentService.queryById(id);
        if (ObjUtil.isNull(aiAgentVo) || !LoginHelper.getUserId().equals(aiAgentVo.getUserId())) {
            return R.fail("Ai agent does not exist");
        }

        return R.ok(iAiAgentService.deleteWithValidByIds(ids, true));
    }

    /**
     * get my Ai agent list
     */
    @GetMapping("/aiAgent/getMyAgentList")
    public R<?> getMyAgentList(AiAgentBo bo) {
        bo.setUserId(LoginHelper.getUserId());
        return R.ok(iAiAgentService.queryList(bo));
    }

    /**
     * create Ai
     */
    @Log(title = "Ai", businessType = BusinessType.INSERT)
    @PostMapping("/aiAgent/createAgent")
    public R<?> createAgent(@Validated(AddGroup.class) @RequestBody AiAgentBo bo) {
        bo.setUserId(LoginHelper.getUserId());
        bo.setStatus("1");

        // upload base64
        if (StringUtils.isNotBlank(bo.getImg()) && bo.getImg().startsWith("data:")) {
            SysOssVo upload = iSysOssService.uploadBase64(bo.getImg());
            bo.setImg(upload.getUrl());
        }
        return R.ok(iAiAgentService.createAgent(bo));
    }

    /**
     * Ai list
     */
    @SaCheckPermission("system:aiAgent:list")
    @GetMapping("/system/aiAgent/list")
    public TableDataInfo<AiAgentVo> list(AiAgentBo bo, PageQuery pageQuery) {
        return iAiAgentService.queryPageList(bo, pageQuery);
    }

    /**
     * Ai export
     */
    @SaCheckPermission("system:aiAgent:export")
    @Log(title = "Ai", businessType = BusinessType.EXPORT)
    @PostMapping("/system/aiAgent/export")
    public void export(AiAgentBo bo, HttpServletResponse response) {
        List<AiAgentVo> list = iAiAgentService.queryList(bo);
        ExcelUtil.exportExcel(list, "Ai", AiAgentVo.class, response);
    }

    /**
     * Ai Info
     *
     * @param id id
     */
    @SaCheckPermission("system:aiAgent:query")
    @GetMapping("/system/aiAgent/{id}")
    public R<AiAgentVo> getInfo(@NotNull(message = "")
                                     @PathVariable Long id) {
        return R.ok(iAiAgentService.queryById(id));
    }

    /**
     * Ai add
     */
    @SaCheckPermission("system:aiAgent:add")
    @Log(title = "Ai", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/system/aiAgent")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiAgentBo bo) {
        return toAjax(iAiAgentService.insertByBo(bo));
    }

    /**
     * Ai edit
     */
    @SaCheckPermission("system:aiAgent:edit")
    @Log(title = "Ai", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/system/aiAgent")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiAgentBo bo) {
        return toAjax(iAiAgentService.updateByBo(bo));
    }

    /**
     * Ai remove
     *
     * @param ids ids
     */
    @SaCheckPermission("system:aiAgent:remove")
    @Log(title = "Ai", businessType = BusinessType.DELETE)
    @DeleteMapping("/system/aiAgent/{ids}")
    public R<Void> remove(@NotEmpty(message = "ids is empty")
                          @PathVariable Long[] ids) {
        return toAjax(iAiAgentService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
