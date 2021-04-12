package com.study.graduation.service;

import com.study.graduation.entity.Task;

import java.util.List;

/**
 * (Task)表服务接口
 *
 * @author makejava
 * @since 2021-04-11 16:28:44
 */
public interface TaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Task queryById(String id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Task> queryAll();

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    Task insert(Task task);

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    Task update(Task task);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     *
     */
    List<Task> listByProject(String projectId);
}
