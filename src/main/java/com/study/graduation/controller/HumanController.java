package com.study.graduation.controller;

import com.study.graduation.dto.ListProjectReq;
import com.study.graduation.entity.MainDocumentList;
import com.study.graduation.entity.Project;
import com.study.graduation.service.DirectoryService;
import com.study.graduation.service.ProjectService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/human")
public class HumanController {
    @Resource
    private DirectoryService directoryService;

    @Resource
    ProjectService projectService;

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, String id){
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
        
        return "human";
    }
}
