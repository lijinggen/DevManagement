package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.dao.ProjectUserRelationDao;
import com.study.graduation.dto.ListProjectReq;
import com.study.graduation.entity.Project;
import com.study.graduation.dao.ProjectDao;
import com.study.graduation.entity.ProjectUserRelation;
import com.study.graduation.service.ProjectService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Project)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 21:17:05
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectDao projectDao;

    @Resource
    private ProjectUserRelationDao projectUserRelationDao;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Project queryById(String id) {
        return this.projectDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Project> queryAllByLimit(int offset, int limit) {
        return this.projectDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project insert(Project project) {
        this.projectDao.insert(project);
        return project;
    }

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project update(Project project) {
        this.projectDao.update(project);
        return this.queryById(project.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.projectDao.deleteById(id) > 0;
    }

    @Override
    public List<Project> list(ListProjectReq req) {
        QueryWrapper<ProjectUserRelation> queryWrapper=new QueryWrapper();
        List<Project> result = new ArrayList<>();
        if(Strings.isNotEmpty(req.getUserId())){
            queryWrapper.lambda().eq(ProjectUserRelation::getUserId,req.getUserId());
            if(req.getRole()!=0){
                queryWrapper.lambda().eq(ProjectUserRelation::getRole,req.getRole());
            }
            List<ProjectUserRelation> projectUserRelations = projectUserRelationDao.selectList(queryWrapper);
            if(projectUserRelations!= null){
                QueryWrapper<Project> projectQueryWrapper=new QueryWrapper<>();
                List<String> ids=projectUserRelations.stream().map(ProjectUserRelation::getProjectId).collect(Collectors.toList());
                projectQueryWrapper.lambda().in(Project::getId,ids);
                List<Project> projects = projectDao.selectList(projectQueryWrapper);
                return projects;
            }else{
                return result;
            }
        }else{
            return null;
        }
    }
}
