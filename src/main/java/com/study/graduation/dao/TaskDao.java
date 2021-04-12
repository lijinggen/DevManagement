package com.study.graduation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.graduation.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Task)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-11 16:28:44
 */
@Mapper
public interface TaskDao extends BaseMapper<Task> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Task queryById(String id);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Task> selectAll();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param task 实例对象
     * @return 对象列表
     */
    List<Task> queryAll(Task task);

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 影响行数
     */
    int insert(Task task);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Task> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Task> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Task> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Task> entities);

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 影响行数
     */
    int update(Task task);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     *
     */
}

