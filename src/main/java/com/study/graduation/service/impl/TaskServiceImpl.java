package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.dto.ListTaskReq;
import com.study.graduation.dto.TaskDto;
import com.study.graduation.entity.Bug;
import com.study.graduation.entity.Task;
import com.study.graduation.dao.TaskDao;
import com.study.graduation.entity.Test;
import com.study.graduation.service.BugService;
import com.study.graduation.service.TaskService;
import com.study.graduation.service.TestService;
import com.study.graduation.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (Task)表服务实现类
 *
 * @author makejava
 * @since 2021-04-11 16:28:45
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskDao taskDao;

    @Resource
    private TestService testService;

    @Resource
    private BugService bugService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Task queryById(String id) {
        return this.taskDao.queryById(id);
    }


    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Task> queryAll() {
        return this.taskDao.selectAll();
    }

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task insert(Task task) {
        this.taskDao.insert(task);
        return task;
    }

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task update(Task task) {
        this.taskDao.update(task);
        return this.queryById(task.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.taskDao.deleteById(id) > 0;
    }

    @Override
    public List<Task> listByProject(String projectId) {
        QueryWrapper<Task>taskQueryWrapper=new QueryWrapper<>();
        taskQueryWrapper.lambda().eq(Task::getProjectId,projectId);
        List<Task> tasks = taskDao.selectList(taskQueryWrapper);
        return tasks;
    }

    @Override
    public List<Task> list(ListTaskReq listTaskReq) {
        QueryWrapper<Task> taskQueryWrapper=new QueryWrapper<>();
        taskQueryWrapper.lambda();
        if(listTaskReq.getUserId()!=null&&listTaskReq.getUserId()!=""){
            taskQueryWrapper.lambda().eq(Task::getUserId,listTaskReq.getUserId());
        }
        if(listTaskReq.getBatchNo()!=null&&listTaskReq.getBatchNo()!=""){
            taskQueryWrapper.lambda().eq(Task::getBatchNo,listTaskReq.getBatchNo());
        }
        return taskDao.selectList(taskQueryWrapper);
    }

    @Override
    public int getICreate(String userId) throws ParseException {
        ListTaskReq listTaskReq=new ListTaskReq();
        List<Task> list = list(listTaskReq);
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
        return res.size();
    }

    @Override
    public void finished(String id) {
        Task task = queryById(id);
        if(task.getType().equals(1)){
            task.setStatus(2);
            update(task);
        }
        if(task.getType().equals(2)){
            ListTaskReq listTaskReq=new ListTaskReq();
            listTaskReq.setBatchNo(task.getBatchNo());
            List<Task> list = list(listTaskReq);
            int flag=1;
            for(Task task1:list){
                if(task1.getType().equals(2)&&task1.getStatus().equals(1)&&!task1.getId().equals(id)){
                    flag=-1;
                }
            }
            if(flag==1){
                for (Task task1 : list) {
                    task1.setStatus(6);
                    update(task1);
                }
            }
            if(flag==-1){
                task.setStatus(2);
                update(task);
            }
        }
        if(task.getType().equals(3)){
            task.setStatus(2);
            update(task);
            ListTaskReq listTaskReq=new ListTaskReq();
            listTaskReq.setBatchNo(task.getBatchNo());
            List<Task> list = list(listTaskReq);
            int flag=-1;
            Task testTask=new Task();
            Bug byTaskId = bugService.getByTaskId(task.getId());
            for (Task task1 : list) {
                if(task1.getType().equals(3)){
                    Bug bb = bugService.getByTaskId(task1.getId());
                    if (task1.getStatus().equals(1)&&!task.getId().equals(id)&&bb.getRelationTaskId().equals(byTaskId.getRelationTaskId())){
                        flag=1;
                    }
                }else if(task1.getType().equals(2)&&task1.getId().equals(byTaskId.getRelationTaskId())){
                    testTask=task1;
                }
            }
            if(flag==-1){
                testTask.setStatus(1);
                update(testTask);
            }
        }
    }

    @Override
    public void closed(String id) {
        Task task=queryById(id);
        if (task.getType().equals(1)){
            task.setStatus(4);
            update(task);
        }
        if (task.getType().equals(2)){
            task.setStatus(4);
            update(task);
        }
        if(task.getType().equals(3)){
            task.setStatus(4);
            update(task);
            ListTaskReq listTaskReq=new ListTaskReq();
            listTaskReq.setBatchNo(task.getBatchNo());
            List<Task> list = list(listTaskReq);
            int flag=-1;
            Task testTask=new Task();
            Bug byTaskId = bugService.getByTaskId(task.getId());
            for (Task task1 : list) {
                if(task1.getType().equals(3)){
                    Bug bb = bugService.getByTaskId(task1.getId());
                    if (task1.getStatus().equals(1)&&!task.getId().equals(id)&&bb.getRelationTaskId().equals(byTaskId.getRelationTaskId())){
                        flag=1;
                    }
                }else if(task1.getType().equals(2)&&task1.getId().equals(byTaskId.getRelationTaskId())){
                    testTask=task1;
                }
            }
            if(flag==-1){
                testTask.setStatus(1);
                update(testTask);
            }
        }
    }

    @Override
    public void reopen(String id) {
        Task task=queryById(id);
        if (task.getType().equals(1)){
            task.setStatus(1);
            update(task);
        }
        if(task.getType().equals(2)){
            task.setStatus(1);
            update(task);
        }
        if(task.getType().equals(3)){
            task.setStatus(1);
            update(task);
            ListTaskReq listTaskReq=new ListTaskReq();
            listTaskReq.setBatchNo(task.getBatchNo());
            List<Task> list = list(listTaskReq);
            Task testTask;
            Bug byTaskId = bugService.getByTaskId(task.getId());
            for (Task task1 : list) {
                if(task1.getType().equals(2)&&task1.getId().equals(byTaskId.getRelationTaskId())){
                    testTask=task1;
                    testTask.setStatus(5);
                    update(testTask);
                }
            }
        }
    }

    @Override
    public void shangXian(String id) {
        Task task=queryById(id);
        ListTaskReq listTaskReq=new ListTaskReq();
        listTaskReq.setBatchNo(task.getBatchNo());
        List<Task> list = list(listTaskReq);
        for (Task task1 : list) {
            task1.setStatus(3);
            update(task1);
        }
    }
}
