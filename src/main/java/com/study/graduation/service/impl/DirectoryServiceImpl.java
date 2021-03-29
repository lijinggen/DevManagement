package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.entity.*;
import com.study.graduation.dao.DirectoryDao;
import com.study.graduation.service.DirectoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Directory)表服务实现类
 *
 * @author makejava
 * @since 2021-03-24 21:36:12
 */
@Service("directoryService")
public class DirectoryServiceImpl implements DirectoryService {
    @Resource
    private DirectoryDao directoryDao;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Directory queryById(String id) {
        return this.directoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Directory> queryAllByLimit(int offset, int limit) {
        return this.directoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param directory 实例对象
     * @return 实例对象
     */
    @Override
    public Directory insert(Directory directory) {
        this.directoryDao.insert(directory);
        return directory;
    }

    /**
     * 修改数据
     *
     * @param directory 实例对象
     * @return 实例对象
     */
    @Override
    public Directory update(Directory directory) {
        this.directoryDao.update(directory);
        return this.queryById(directory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.directoryDao.deleteById(id) > 0;
    }

    @Override
    public MainDocumentList listByProject(String projectId) {
        // 该项目下各个 tab存储的东西
        SubDocumentList demand=new SubDocumentList();
        SubDocumentList stander=new SubDocumentList();
        SubDocumentList dev=new SubDocumentList();
        SubDocumentList other=new SubDocumentList();
        // 主列表
        MainDocumentList documentLists=new MainDocumentList(demand,stander,dev,other);
        QueryWrapper<Directory> directoryQueryWrapper=new QueryWrapper<>();
        directoryQueryWrapper.lambda().eq(Directory::getProjectId,projectId);
        // 1. 查出所有目录，归属到各个tab
        // 2. 查出所有document，归属到①tab ②目录
        List<Directory> directories = directoryDao.selectList(directoryQueryWrapper);
        if(directories!=null&&directories.size()>0){
            directories.forEach(c->{
                DirectoryAndDocList directoryAndDocList = new DirectoryAndDocList();
                directoryAndDocList.setDirectory(c);
                if(c.getType()==1){
                    demand.getDirectories().add(directoryAndDocList);
                }else if(c.getType()==2){
                    stander.getDirectories().add(directoryAndDocList);
                }else if(c.getType()==3){
                    dev.getDirectories().add(directoryAndDocList);
                }else if(c.getType()==4){
                    other.getDirectories().add(directoryAndDocList);
                }
            });
        }
        return documentLists;
    }
}
