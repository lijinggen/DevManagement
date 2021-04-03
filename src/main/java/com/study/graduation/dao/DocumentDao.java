package com.study.graduation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.graduation.entity.Document;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Document)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-29 13:08:54
 */
@Mapper
public interface DocumentDao extends BaseMapper<Document> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Document queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Document> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param document 实例对象
     * @return 对象列表
     */
    List<Document> queryAll(Document document);

    /**
     * 新增数据
     *
     * @param document 实例对象
     * @return 影响行数
     */
    int insert(Document document);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Document> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Document> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Document> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Document> entities);

    /**
     * 修改数据
     *
     * @param document 实例对象
     * @return 影响行数
     */
    int update(Document document);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

