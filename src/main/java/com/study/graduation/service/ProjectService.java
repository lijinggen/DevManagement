package com.study.graduation.service;

import com.study.graduation.dto.ListProjectReq;
import com.study.graduation.entity.Project;

import java.util.List;

/**
 * (Project)表服务接口
 *
 * @author makejava
 * @since 2021-03-23 21:17:05
 */
public interface ProjectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Project queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Project> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    Project insert(Project project);

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    Project update(Project project);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 查询列表
     *
     * @param req 主键
     * @return 列表
     */
    List<Project> list(ListProjectReq req);
}
