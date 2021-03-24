package com.study.graduation.controller;

import com.study.graduation.entity.ProjectUserRelation;
import com.study.graduation.service.ProjectUserRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ProjectUserRelation)表控制层
 *
 * @author makejava
 * @since 2021-03-23 21:20:58
 */
@RestController
@RequestMapping("projectUserRelation")
public class ProjectUserRelationController {
    /**
     * 服务对象
     */
    @Resource
    private ProjectUserRelationService projectUserRelationService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ProjectUserRelation selectOne(String id) {
        return this.projectUserRelationService.queryById(id);
    }

}
