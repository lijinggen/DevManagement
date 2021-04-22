package com.study.graduation.controller;

import com.study.graduation.config.Result;
import com.study.graduation.dto.AddUserToProjectReq;
import com.study.graduation.dto.ListProjectReq;
import com.study.graduation.dto.ProjectUser;
import com.study.graduation.dto.RoleEnum;
import com.study.graduation.entity.Project;
import com.study.graduation.entity.ProjectUserRelation;
import com.study.graduation.entity.User;
import com.study.graduation.service.DirectoryService;
import com.study.graduation.service.ProjectService;
import com.study.graduation.service.ProjectUserRelationService;
import com.study.graduation.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/human")
public class HumanController {
    @Resource
    private DirectoryService directoryService;

    @Resource
    private UserService userService;

    @Resource
    ProjectService projectService;

    @Resource
    ProjectUserRelationService projectUserRelationService;

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, String id,String keyword){
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
        model.addAttribute("project_list",resultList);
        if (Strings.isNotEmpty(id)){
            List<ProjectUserRelation> projectUserRelations = projectUserRelationService.listByProject(id);
            List<ProjectUser> lists=new ArrayList<>();
            for (ProjectUserRelation item : projectUserRelations) {
                ProjectUser projectUser=new ProjectUser();
                projectUser.setRoleNum(item.getRole());
                projectUser.setUserId(item.getUserId());
                projectUser.setProjectUserRelactionId(item.getId());
                projectUser.setRole(RoleEnum.RoleName[item.getRole()-1]);
                User user = userService.queryById(item.getUserId());
                if(user!=null){
                    projectUser.setUserName(user.getUserName());
                    if(Strings.isNotEmpty(keyword)&&!user.getUserName().contains(keyword)){
                        continue;
                    }
                }
                lists.add(projectUser);
            }
            model.addAttribute("project_name",projectService.queryById(id).getName());
            model.addAttribute("project_id",id);
            model.addAttribute("member_list",lists);
        }
        List<User> addUserList;
        addUserList=userService.listFilterByProjectId(id);
        model.addAttribute("add_user_list",addUserList);
        return "human";
    }

    @PostMapping("/addUserToProject")
    @ResponseBody
    public Result<String> addToProject(AddUserToProjectReq addUserToProjectReq){
        ProjectUserRelation projectUserRelation=new ProjectUserRelation();
        projectUserRelation.setId(UUID.randomUUID().toString());
        projectUserRelation.setProjectId(addUserToProjectReq.getProjectId());
        projectUserRelation.setUserId(addUserToProjectReq.getUserId());
        projectUserRelation.setCreateTime(new Date());
        projectUserRelation.setModifyTime(new Date());
        projectUserRelation.setRole(addUserToProjectReq.getRole());
        projectUserRelationService.insert(projectUserRelation);
        return new Result("true");
    }

    @PostMapping("rem")
    @ResponseBody
    public Result<String> rem(String id){
        projectUserRelationService.deleteById(id);
        return new Result<>("true");
    }
}
