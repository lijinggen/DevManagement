package com.study.graduation.dao;

import com.study.graduation.entity.Directory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Directory)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-24 21:36:12
 */
@Mapper
public interface DirectoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Directory queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Directory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param directory 实例对象
     * @return 对象列表
     */
    List<Directory> queryAll(Directory directory);

    /**
     * 新增数据
     *
     * @param directory 实例对象
     * @return 影响行数
     */
    int insert(Directory directory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Directory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Directory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Directory> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Directory> entities);

    /**
     * 修改数据
     *
     * @param directory 实例对象
     * @return 影响行数
     */
    int update(Directory directory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

