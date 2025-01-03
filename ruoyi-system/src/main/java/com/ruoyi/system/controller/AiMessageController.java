package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.system.domain.vo.AiAgentVo;
import com.ruoyi.system.service.IAiAgentService;
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
import com.ruoyi.system.domain.vo.AiMessageVo;
import com.ruoyi.system.domain.bo.AiMessageBo;
import com.ruoyi.system.service.IAiMessageService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * Ai message
 *
 * @author ruoyi
 * @date 2024-12-23
 */
@Validated
@RequiredArgsConstructor
@RestController
public class AiMessageController extends BaseController {

    private final IAiMessageService iAiMessageService;

    private final IAiAgentService iAiAgentService;

    /**
     * Ai add
     */
    @Log(title = "Ai", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/aiMessage/addMessage")
    public R<Void> addMessage(@Validated(AddGroup.class) @RequestBody AiMessageBo bo) {
        bo.setUserId(LoginHelper.getUserId());

        AiAgentVo aiAgentVo = iAiAgentService.queryById(bo.getAiAgentId());
        if (ObjUtil.isNull(aiAgentVo) || !LoginHelper.getUserId().equals(aiAgentVo.getUserId())) {
            return R.fail("Ai agent does not exist");
        }

        return toAjax(iAiMessageService.insertByBo(bo));
    }

    /**
     * Ai list
     */
    @GetMapping("/aiMessage/getMessageList")
    public TableDataInfo<AiMessageVo> getMessageList(AiMessageBo bo, PageQuery pageQuery) {
        if (ObjUtil.isNull(bo.getAiAgentId())) {
            throw new ServiceException("Missing ai agent id");
        }

        AiAgentVo aiAgentVo = iAiAgentService.queryById(bo.getAiAgentId());
        if (ObjUtil.isNull(aiAgentVo) || !LoginHelper.getUserId().equals(aiAgentVo.getUserId())) {
            throw new ServiceException("Ai agent does not exist");
        }

        if (ObjUtil.isNull(pageQuery.getPageSize())) {
            pageQuery.setPageSize(10000);
        }
        return iAiMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * Ai delete
     *
     * @param id id
     */
    @Log(title = "Ai", businessType = BusinessType.DELETE)
    @DeleteMapping("/aiMessage/delMessage/{id}")
    public R<Void> delMessage(@PathVariable Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);

        AiMessageVo aiMessageVo = iAiMessageService.queryById(id);
        if (ObjUtil.isNull(aiMessageVo) || !LoginHelper.getUserId().equals(aiMessageVo.getUserId())) {
            throw new ServiceException("Record does not exist");
        }

        return toAjax(iAiMessageService.deleteWithValidByIds(ids, true));
    }

    /**
     * Ai list
     */
    @SaCheckPermission("system:aiMessage:list")
    @GetMapping("/system/aiMessage/list")
    public TableDataInfo<AiMessageVo> list(AiMessageBo bo, PageQuery pageQuery) {
        return iAiMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * Ai export
     */
    @SaCheckPermission("system:aiMessage:export")
    @Log(title = "Ai", businessType = BusinessType.EXPORT)
    @PostMapping("/system/aiMessage/export")
    public void export(AiMessageBo bo, HttpServletResponse response) {
        List<AiMessageVo> list = iAiMessageService.queryList(bo);
        ExcelUtil.exportExcel(list, "Ai", AiMessageVo.class, response);
    }

    /**
     * Ai Info
     *
     * @param id id
     */
    @SaCheckPermission("system:aiMessage:query")
    @GetMapping("/system/aiMessage/{id}")
    public R<AiMessageVo> getInfo(@NotNull(message = "id is null")
                                     @PathVariable Long id) {
        return R.ok(iAiMessageService.queryById(id));
    }

    /**
     * Ai add
     */
    @SaCheckPermission("system:aiMessage:add")
    @Log(title = "Ai", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/system/aiMessage")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiMessageBo bo) {
        return toAjax(iAiMessageService.insertByBo(bo));
    }

    /**
     * Ai edit
     */
    @SaCheckPermission("system:aiMessage:edit")
    @Log(title = "Ai", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/system/aiMessage")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiMessageBo bo) {
        return toAjax(iAiMessageService.updateByBo(bo));
    }

    /**
     * Ai remove
     *
     * @param ids ids
     */
    @SaCheckPermission("system:aiMessage:remove")
    @Log(title = "Ai", businessType = BusinessType.DELETE)
    @DeleteMapping("/system/aiMessage/{ids}")
    public R<Void> remove(@NotEmpty(message = "ids is empty")
                          @PathVariable Long[] ids) {
        return toAjax(iAiMessageService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
