package com.study.graduation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.graduation.entity.ProjectUserRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ProjectUserRelation)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-23 21:20:58
 */
@Mapper
public interface ProjectUserRelationDao extends BaseMapper<ProjectUserRelation>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectUserRelation queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ProjectUserRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectUserRelation 实例对象
     * @return 对象列表
     */
    List<ProjectUserRelation> queryAll(ProjectUserRelation projectUserRelation);

    /**
     * 新增数据
     *
     * @param projectUserRelation 实例对象
     * @return 影响行数
     */
    int insert(ProjectUserRelation projectUserRelation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProjectUserRelation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ProjectUserRelation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProjectUserRelation> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<ProjectUserRelation> entities);

    /**
     * 修改数据
     *
     * @param projectUserRelation 实例对象
     * @return 影响行数
     */
    int update(ProjectUserRelation projectUserRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

