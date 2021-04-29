package com.study.graduation.controller;

import com.study.graduation.dto.ListTaskReq;
import com.study.graduation.dto.TaskDto;
import com.study.graduation.entity.Task;
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
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Resource
    private TaskService taskService;

    @RequestMapping("/index")
    public String home(HttpServletRequest request,Model model, String tabIndex) throws ParseException {
        if(Strings.isEmpty(tabIndex)){
            model.addAttribute("tabIndex",1);
        }else{
            model.addAttribute("tabIndex",Integer.parseInt(tabIndex));
            if(tabIndex.equals("3")){
                ListTaskReq listTaskReq=new ListTaskReq();
                String userId = (String)request.getSession().getAttribute("user_id");
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
                String userId = (String)request.getSession().getAttribute("user_id");
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
                String userId = (String)request.getSession().getAttribute("user_id");
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
                model.addAttribute("iCreate",res.size());
                model.addAttribute("task_list",res);
            }
        }
        return "home";
    }
}
