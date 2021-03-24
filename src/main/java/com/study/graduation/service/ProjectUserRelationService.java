package com.study.graduation.service;

import com.study.graduation.entity.ProjectUserRelation;

import java.util.List;

/**
 * (ProjectUserRelation)表服务接口
 *
 * @author makejava
 * @since 2021-03-23 21:20:58
 */
public interface ProjectUserRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectUserRelation queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ProjectUserRelation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param projectUserRelation 实例对象
     * @return 实例对象
     */
    ProjectUserRelation insert(ProjectUserRelation projectUserRelation);

    /**
     * 修改数据
     *
     * @param projectUserRelation 实例对象
     * @return 实例对象
     */
    ProjectUserRelation update(ProjectUserRelation projectUserRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
