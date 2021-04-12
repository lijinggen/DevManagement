package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.entity.Task;
import com.study.graduation.dao.TaskDao;
import com.study.graduation.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Task)表服务实现类
 *
 * @author makejava
 * @since 2021-04-11 16:28:45
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskDao taskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Task queryById(String id) {
        return this.taskDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Task> queryAll() {
        return this.taskDao.selectAll();
    }

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task insert(Task task) {
        this.taskDao.insert(task);
        return task;
    }

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task update(Task task) {
        this.taskDao.update(task);
        return this.queryById(task.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.taskDao.deleteById(id) > 0;
    }

    @Override
    public List<Task> listByProject(String projectId) {
        QueryWrapper<Task>taskQueryWrapper=new QueryWrapper<>();
        taskQueryWrapper.lambda().eq(Task::getProjectId,projectId);
        List<Task> tasks = taskDao.selectList(taskQueryWrapper);
        return tasks;
    }
}
