package com.study.graduation.controller;

import com.study.graduation.entity.Task;
import com.study.graduation.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Task)表控制层
 *
 * @author makejava
 * @since 2021-04-11 16:28:45
 */
@RestController
@RequestMapping("task")
public class TaskController {
    /**
     * 服务对象
     */
    @Resource
    private TaskService taskService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Task selectOne(String id) {
        return this.taskService.queryById(id);
    }

    @GetMapping("finished")
    public void finished(String id){
        Task task = taskService.queryById(id);
        task.setStatus(2);
        taskService.update(task);
    }

    @GetMapping("closed")
    public void closed(String id){
        taskService.closed(id);
    }

    @GetMapping("reopen")
    public void reopen(String id){
        taskService.reopen(id);
    }
}
