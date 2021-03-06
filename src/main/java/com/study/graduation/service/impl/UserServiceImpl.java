package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.entity.ProjectUserRelation;
import com.study.graduation.entity.User;
import com.study.graduation.dao.UserDao;
import com.study.graduation.service.ProjectUserRelationService;
import com.study.graduation.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-03-15 20:13:23
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private ProjectUserRelationService projectUserRelationService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public User getByUserAccount(String userAccount) {
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        queryWrapper.lambda().eq(User::getUserAccount,userAccount);
        User user = userDao.selectOne(queryWrapper);
        return user;
    }

    @Override
    public List<User> listFilterByProjectId(String projectId) {
        List<User> users = userDao.selectList(new QueryWrapper<>());
        List<ProjectUserRelation> projectUserRelations=projectUserRelationService.listByProject(projectId);
        Map<String, String> collect = projectUserRelations.stream().collect(Collectors.toMap(ProjectUserRelation::getUserId, ProjectUserRelation::getId));
        List<User> results=new ArrayList<>();
        for (User user : users) {
            if(collect.get(user.getId())==null){
                results.add(user);
            }
        }
        return results;
    }

    @Override
    public List<User> getProjectDevMember(String projectId) {
        List<User> users = userDao.selectList(new QueryWrapper<>());
        List<ProjectUserRelation> projectUserRelations=projectUserRelationService.listByProject(projectId);
        Map<String, Integer> collect = projectUserRelations.stream().collect(Collectors.toMap(ProjectUserRelation::getUserId, ProjectUserRelation::getRole));
        List<User> results=new ArrayList<>();
        for (User user : users) {
            Integer role = collect.get(user.getId());
            if(role!=null){
                if(role.equals(2)){
                    results.add(user);
                }
            }
        }
        return results;
    }

    @Override
    public List<User> getProjectTestMember(String projectId) {
        List<User> users = userDao.selectList(new QueryWrapper<>());
        List<ProjectUserRelation> projectUserRelations=projectUserRelationService.listByProject(projectId);
        Map<String, Integer> collect = projectUserRelations.stream().collect(Collectors.toMap(ProjectUserRelation::getUserId, ProjectUserRelation::getRole));
        List<User> results=new ArrayList<>();
        for (User user : users) {
            Integer role = collect.get(user.getId());
            if(role!=null){
                if(role.equals(3)){
                    results.add(user);
                }
            }
        }
        return results;
    }


}
