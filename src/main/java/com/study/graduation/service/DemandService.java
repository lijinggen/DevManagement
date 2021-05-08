package com.study.graduation.service;

import com.study.graduation.entity.Demand;

import java.util.List;

/**
 * (Demand)表服务接口
 *
 * @author makejava
 * @since 2021-04-26 12:52:52
 */
public interface DemandService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Demand queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Demand> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param demand 实例对象
     * @return 实例对象
     */
    Demand insert(Demand demand);

    /**
     * 修改数据
     *
     * @param demand 实例对象
     * @return 实例对象
     */
    Demand update(Demand demand);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    Demand getByTaskId(String taskId);

}
