package com.study.graduation.service.impl;

import com.study.graduation.entity.Bug;
import com.study.graduation.dao.BugDao;
import com.study.graduation.service.BugService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Bug)表服务实现类
 *
 * @author makejava
 * @since 2021-05-07 21:10:16
 */
@Service("bugService")
public class BugServiceImpl implements BugService {
    @Resource
    private BugDao bugDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Bug queryById(String id) {
        return this.bugDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Bug> queryAllByLimit(int offset, int limit) {
        return this.bugDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param bug 实例对象
     * @return 实例对象
     */
    @Override
    public Bug insert(Bug bug) {
        this.bugDao.insert(bug);
        return bug;
    }

    /**
     * 修改数据
     *
     * @param bug 实例对象
     * @return 实例对象
     */
    @Override
    public Bug update(Bug bug) {
        this.bugDao.update(bug);
        return this.queryById(bug.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.bugDao.deleteById(id) > 0;
    }
}
