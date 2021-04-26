package com.study.graduation.controller;

import com.study.graduation.config.Result;
import com.study.graduation.dto.*;
import com.study.graduation.entity.*;
import com.study.graduation.service.ProjectService;
import com.study.graduation.service.ProjectUserRelationService;
import com.study.graduation.service.TaskService;
import com.study.graduation.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.*;

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
    private UserService userService;

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
            model.addAttribute("role_name", RoleEnum.RoleName[projectUserRelation.getRole()-1]);
        }
        //-------------------------------------------------------------------基本数据
        List<TaskUserDto> taskUserList=projectService.listTask(id,status);
        model.addAttribute("task_list",taskUserList);
        if(status==null){
            status="0";
        }
        model.addAttribute("status",Integer.parseInt(status));
        float progress=0;
        int bottom;
        int top;
        if(taskUserList!=null&&taskUserList.size()>0){
            bottom=0;
            top=0;
            for (TaskUserDto taskUserDto : taskUserList) {
                for (TaskDto b : taskUserDto.getTaskDtoList()) {
                    if(b.getStatus().equals(2)||b.getStatus().equals(3)
                            ||b.getStatus().equals(4)||b.getStatus().equals(6)){
                        top++;
                    }
                    bottom++;
                }
            }
            progress=((float)top/(float)bottom)*100;
        }else{
            progress=100;
        }
        String format=String.format("%.2f",progress);
        model.addAttribute("progress",format);
        return "project";
    }

    @RequestMapping("/demand")
    public String addDemand(Model model,String projectId){
        model.addAttribute("project_id",projectId);
        List<User> addUserList;
        addUserList=userService.getProjectDevMember(projectId);
        model.addAttribute("add_user_list",addUserList);
        return "demand";
    }

    @PostMapping("/addDemandReq")
    public String addDemandReq(HttpServletRequest request, AddDemandRequest addDemandRequest,@RequestParam("fileList") MultipartFile []fileList){
        String userId=(String)request.getSession().getAttribute("user_id");
        projectService.addDemand(addDemandRequest,fileList,userId);
        return "redirect:index?id="+addDemandRequest.getProjectId() ;
    }

    @PostMapping("/addProject")
    public String addProject(Model model,HttpServletRequest request,String projectName){
        Project byName = projectService.getByName(projectName);
        if(byName!=null){
            model.addAttribute("failed","failed");
            return "redirect:index";
        }
        String userId = (String)request.getSession().getAttribute("user_id");
        Project project=new Project();
        project.setCreateTime(new Date());
        project.setModifyTime(new Date());
        project.setName(projectName);
        project.setId(UUID.randomUUID().toString());
        projectService.insert(project);
        ProjectUserRelation projectUserRelation=new ProjectUserRelation();
        projectUserRelation.setRole(5);
        projectUserRelation.setModifyTime(new Date());
        projectUserRelation.setCreateTime(new Date());
        projectUserRelation.setUserId(userId);
        projectUserRelation.setProjectId(project.getId());
        projectUserRelation.setId(UUID.randomUUID().toString());
        projectUserRelationService.insert(projectUserRelation);
        model.addAttribute("success","success");
        return "redirect:index";
    }
}
