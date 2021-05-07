package com.study.graduation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.graduation.entity.Bug;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Bug)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-07 21:10:16
 */
@Mapper
public interface BugDao extends BaseMapper<Bug> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Bug queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Bug> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param bug 实例对象
     * @return 对象列表
     */
    List<Bug> queryAll(Bug bug);

    /**
     * 新增数据
     *
     * @param bug 实例对象
     * @return 影响行数
     */
    int insert(Bug bug);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Bug> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Bug> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Bug> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Bug> entities);

    /**
     * 修改数据
     *
     * @param bug 实例对象
     * @return 影响行数
     */
    int update(Bug bug);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

