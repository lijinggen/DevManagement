package com.study.graduation.controller;

import com.study.graduation.entity.Bug;
import com.study.graduation.service.BugService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Bug)表控制层
 *
 * @author makejava
 * @since 2021-05-07 21:10:16
 */
@RestController
@RequestMapping("bug")
public class BugController {
    /**
     * 服务对象
     */
    @Resource
    private BugService bugService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Bug selectOne(String id) {
        return this.bugService.queryById(id);
    }

}
