package com.study.graduation.service;

import com.study.graduation.entity.Directory;
import com.study.graduation.entity.MainDocumentList;

import java.util.List;

/**
 * (Directory)表服务接口
 *
 * @author makejava
 * @since 2021-03-24 21:36:12
 */
public interface DirectoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Directory queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Directory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param directory 实例对象
     * @return 实例对象
     */
    Directory insert(Directory directory);

    /**
     * 修改数据
     *
     * @param directory 实例对象
     * @return 实例对象
     */
    Directory update(Directory directory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    MainDocumentList listByProject(String projectId);

    boolean selectByNameAndType(String name, int type);
}
