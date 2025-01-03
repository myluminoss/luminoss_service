package com.ruoyi.system.controller;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * check in
 *
 * @author ruoyi
 * @date 2024-12-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    private final TaskService taskService;

    @GetMapping("/taskInfo")
    public R<?> getTaskInfo() {
        return R.ok(taskService.getTaskInfo());
    }

}
