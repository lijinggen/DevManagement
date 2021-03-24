package com.study.graduation.controller;

import com.study.graduation.config.Result;
import com.study.graduation.dto.ListProjectReq;
import com.study.graduation.entity.Project;
import com.study.graduation.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (Project)表控制层
 *
 * @author makejava
 * @since 2021-03-23 21:17:05
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    /**
     * 服务对象
     */
    @Resource
    private ProjectService projectService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @ResponseBody
    public Result<Project> selectOne(String id) {
        return new Result<>(this.projectService.queryById(id));
    }

    @PostMapping("/list")
    @ResponseBody
    public Result<List<Project>> list(HttpServletRequest request, ListProjectReq req){
        String userId = (String)request.getSession().getAttribute("user_id");
        req.setUserId(userId);
        List<Project> list = projectService.list(req);
        return new Result<>(list);
    }
}
