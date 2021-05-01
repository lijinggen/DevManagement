package com.study.graduation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.graduation.entity.Demand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Demand)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-26 12:52:52
 */
@Mapper
public interface DemandDao extends BaseMapper<Demand> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Demand queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Demand> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param demand 实例对象
     * @return 对象列表
     */
    List<Demand> queryAll(Demand demand);

    /**
     * 新增数据
     *
     * @param demand 实例对象
     * @return 影响行数
     */
    int insert(Demand demand);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Demand> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Demand> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Demand> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Demand> entities);

    /**
     * 修改数据
     *
     * @param demand 实例对象
     * @return 影响行数
     */
    int update(Demand demand);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

