package com.study.graduation.controller;

import com.study.graduation.entity.Demand;
import com.study.graduation.service.DemandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Demand)表控制层
 *
 * @author makejava
 * @since 2021-04-26 12:52:52
 */
@RestController
@RequestMapping("demand")
public class DemandController {
    /**
     * 服务对象
     */
    @Resource
    private DemandService demandService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Demand selectOne(String id) {
        return this.demandService.queryById(id);
    }

}
