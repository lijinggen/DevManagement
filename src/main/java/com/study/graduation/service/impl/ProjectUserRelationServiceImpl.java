package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.entity.ProjectUserRelation;
import com.study.graduation.dao.ProjectUserRelationDao;
import com.study.graduation.service.ProjectUserRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ProjectUserRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 21:20:58
 */
@Service("projectUserRelationService")
public class ProjectUserRelationServiceImpl implements ProjectUserRelationService {
    @Resource
    private ProjectUserRelationDao projectUserRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProjectUserRelation queryById(String id) {
        return this.projectUserRelationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ProjectUserRelation> queryAllByLimit(int offset, int limit) {
        return this.projectUserRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param projectUserRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ProjectUserRelation insert(ProjectUserRelation projectUserRelation) {
        this.projectUserRelationDao.insert(projectUserRelation);
        return projectUserRelation;
    }

    /**
     * 修改数据
     *
     * @param projectUserRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ProjectUserRelation update(ProjectUserRelation projectUserRelation) {
        this.projectUserRelationDao.update(projectUserRelation);
        return this.queryById(projectUserRelation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.projectUserRelationDao.deleteById(id) > 0;
    }

    @Override
    public List<ProjectUserRelation> listByProject(String projectId) {
        QueryWrapper<ProjectUserRelation> pURQueryWrapper = new QueryWrapper<>();
        pURQueryWrapper.lambda().eq(ProjectUserRelation::getProjectId,projectId);
        List<ProjectUserRelation> projectUserRelations = projectUserRelationDao.selectList(pURQueryWrapper);
        return projectUserRelations;
    }

    @Override
    public ProjectUserRelation getByUserId(String userId,String projectId) {
        QueryWrapper<ProjectUserRelation> projectUserRelationQueryWrapper=new QueryWrapper<>();
        projectUserRelationQueryWrapper.lambda().eq(ProjectUserRelation::getUserId,userId);
        projectUserRelationQueryWrapper.lambda().eq(ProjectUserRelation::getProjectId,projectId);
        ProjectUserRelation projectUserRelation = projectUserRelationDao.selectOne(projectUserRelationQueryWrapper);
        return projectUserRelation;
    }
}
