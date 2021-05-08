package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.entity.Demand;
import com.study.graduation.dao.DemandDao;
import com.study.graduation.service.DemandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Demand)表服务实现类
 *
 * @author makejava
 * @since 2021-04-26 12:52:52
 */
@Service("demandService")
public class DemandServiceImpl implements DemandService {
    @Resource
    private DemandDao demandDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Demand queryById(String id) {
        return this.demandDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Demand> queryAllByLimit(int offset, int limit) {
        return this.demandDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param demand 实例对象
     * @return 实例对象
     */
    @Override
    public Demand insert(Demand demand) {
        this.demandDao.insert(demand);
        return demand;
    }

    /**
     * 修改数据
     *
     * @param demand 实例对象
     * @return 实例对象
     */
    @Override
    public Demand update(Demand demand) {
        this.demandDao.update(demand);
        return this.queryById(demand.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.demandDao.deleteById(id) > 0;
    }

    @Override
    public Demand getByTaskId(String taskId) {
        QueryWrapper<Demand> demandQueryWrapper=new QueryWrapper<>();
        demandQueryWrapper.lambda().eq(Demand::getTaskId,taskId);
        Demand demand = demandDao.selectOne(demandQueryWrapper);
        return demand;
    }
}
