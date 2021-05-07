package com.study.graduation.service;

import com.study.graduation.entity.Bug;

import java.util.List;

/**
 * (Bug)表服务接口
 *
 * @author makejava
 * @since 2021-05-07 21:10:16
 */
public interface BugService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Bug queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Bug> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param bug 实例对象
     * @return 实例对象
     */
    Bug insert(Bug bug);

    /**
     * 修改数据
     *
     * @param bug 实例对象
     * @return 实例对象
     */
    Bug update(Bug bug);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
