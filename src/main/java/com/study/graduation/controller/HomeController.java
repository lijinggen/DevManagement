package com.study.graduation.controller;

import com.study.graduation.dto.ListProjectReq;
import com.study.graduation.dto.ListTaskReq;
import com.study.graduation.dto.TaskDto;
import com.study.graduation.entity.Project;
import com.study.graduation.entity.Task;
import com.study.graduation.service.ProjectService;
import com.study.graduation.service.TaskService;
import com.study.graduation.util.DateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Resource
    private TaskService taskService;

    @Resource
    private ProjectService projectService;

    @RequestMapping("/index")
    public String home(HttpServletRequest request,Model model, String tabIndex) throws ParseException {
        String userId = (String)request.getSession().getAttribute("user_id");
        if(Strings.isEmpty(tabIndex)){
            model.addAttribute("tabIndex",1);
        }else{
            model.addAttribute("tabIndex",Integer.parseInt(tabIndex));
            if(tabIndex.equals("3")){
                ListTaskReq listTaskReq=new ListTaskReq();
                listTaskReq.setUserId(userId);
                List<Task> list = taskService.list(listTaskReq);
                List<TaskDto> res=new ArrayList<>();
                for (Task task : list) {
                    if(task.getStatus().equals(1)||task.getStatus().equals(5)){
                        TaskDto taskDto=new TaskDto();
                        taskDto.setEndTime(DateUtil.format(task.getEndTime()));
                        taskDto.setTitle(task.getTitle());
                        taskDto.setId(task.getId());
                        res.add(taskDto);
                    }
                }
                model.addAttribute("task_list",res);
            }
            if(tabIndex.equals("4")){
                ListTaskReq listTaskReq=new ListTaskReq();
                listTaskReq.setUserId(userId);
                List<Task> list = taskService.list(listTaskReq);
                List<TaskDto> res=new ArrayList<>();
                for (Task task : list) {
                    if(task.getStatus().equals(2)||task.getStatus().equals(3)||task.getStatus().equals(4)
                            ||task.getStatus().equals(6)){
                        TaskDto taskDto=new TaskDto();
                        taskDto.setEndTime(DateUtil.format(task.getEndTime()));
                        taskDto.setTitle(task.getTitle());
                        taskDto.setId(task.getId());
                        res.add(taskDto);
                    }
                }
                model.addAttribute("task_list",res);
            }
            if(tabIndex.equals("5")){
                ListTaskReq listTaskReq=new ListTaskReq();
                listTaskReq.setUserId(userId);
                List<Task> list = taskService.list(listTaskReq);
                List<TaskDto> res=new ArrayList<>();
                for (Task task : list) {
                    if(task.getCreateUser().equals(userId)){
                        TaskDto taskDto=new TaskDto();
                        taskDto.setEndTime(DateUtil.format(task.getEndTime()));
                        taskDto.setTitle(task.getTitle());
                        taskDto.setId(task.getId());
                        res.add(taskDto);
                    }
                }
                model.addAttribute("task_list",res);
            }
        }
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
        return "home";
    }
}
