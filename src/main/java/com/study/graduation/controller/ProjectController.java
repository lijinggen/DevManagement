package com.study.graduation.controller;

import com.study.graduation.config.Result;
import com.study.graduation.dto.ListProjectReq;
import com.study.graduation.dto.TaskDto;
import com.study.graduation.dto.TaskUserDto;
import com.study.graduation.entity.*;
import com.study.graduation.service.ProjectService;
import com.study.graduation.service.ProjectUserRelationService;
import com.study.graduation.service.TaskService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ProjectUserRelationService projectUserRelationService;

    @Resource
    private TaskService taskService;
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

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request,String id,String status) throws ParseException {
        String userId = (String)request.getSession().getAttribute("user_id");
        ListProjectReq listProjectReq = new ListProjectReq();
        listProjectReq.setUserId(userId);
        List<Project> list = projectService.list(listProjectReq);
        List<Map<String,Object>>resultList=new ArrayList<>();
        if(list!=null&&list.size()>0){
            for (Project project : list) {
                Map<String,Object> item =new HashMap<>();
                item.put("id",project.getId());
                item.put("name",project.getName());
                resultList.add(item);
            }
        }
        if(Strings.isNotEmpty(id)){
            model.addAttribute("project_name",projectService.queryById(id).getName());
        }
        model.addAttribute("project_id",id);
        model.addAttribute("project_list",resultList);
        ProjectUserRelation projectUserRelation = projectUserRelationService.getByUserId(userId, id);
        if(projectUserRelation!=null){
            model.addAttribute("role",projectUserRelation.getRole());
        }
        //-------------------------------------------------------------------基本数据
        List<TaskUserDto> taskUserList=projectService.listTask(id,status);
        model.addAttribute("task_list",taskUserList);
        if(status==null){
            status="0";
        }
        model.addAttribute("status",Integer.parseInt(status));
        return "project";
    }
}
