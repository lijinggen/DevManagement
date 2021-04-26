package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.dao.ProjectUserRelationDao;
import com.study.graduation.dao.TaskDao;
import com.study.graduation.dto.*;
import com.study.graduation.entity.*;
import com.study.graduation.dao.ProjectDao;
import com.study.graduation.service.ProjectService;
import com.study.graduation.service.ProjectUserRelationService;
import com.study.graduation.service.TaskService;
import com.study.graduation.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (Project)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 21:17:05
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectDao projectDao;

    @Resource
    private ProjectUserRelationDao projectUserRelationDao;

    @Resource
    private ProjectUserRelationService projectUserRelationService;

    @Resource
    private TaskDao taskDao;

    @Resource
    private TaskService taskService;

    @Resource
    private UserService userService;

    @Value("${customFile}")
    public String uploadDir;

    private static String[] taskType={"需求","测试","bug"};

    private static String[] borderColor={"border-left: 4px solid #DC3545;","border-left: 4px solid #FFC107;",
                                        "border-left: 4px solid #28A745;","border-left: 4px solid #6C7586;"};

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Project queryById(String id) {
        return this.projectDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Project> queryAllByLimit(int offset, int limit) {
        return this.projectDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project insert(Project project) {
        this.projectDao.insert(project);
        return project;
    }

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project update(Project project) {
        this.projectDao.update(project);
        return this.queryById(project.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.projectDao.deleteById(id) > 0;
    }

    @Override
    public List<Project> list(ListProjectReq req) {
        QueryWrapper<ProjectUserRelation> queryWrapper=new QueryWrapper();
        List<Project> result = new ArrayList<>();
        if(Strings.isNotEmpty(req.getUserId())){
            queryWrapper.lambda().eq(ProjectUserRelation::getUserId,req.getUserId());
            if(req.getRole()!=0){
                queryWrapper.lambda().eq(ProjectUserRelation::getRole,req.getRole());
            }
            List<ProjectUserRelation> projectUserRelations = projectUserRelationDao.selectList(queryWrapper);
            if(projectUserRelations!= null){
                QueryWrapper<Project> projectQueryWrapper=new QueryWrapper<>();
                List<String> ids=projectUserRelations.stream().map(ProjectUserRelation::getProjectId).collect(Collectors.toList());
                projectQueryWrapper.lambda().in(Project::getId,ids);
                List<Project> projects = projectDao.selectList(projectQueryWrapper);
                return projects;
            }else{
                return result;
            }
        }else{
            return null;
        }
    }

    public int hoursBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60));
    }

    @Override
    public List<TaskUserDto> listTask(String projectId,String status) throws ParseException {
        List<Task> taskList = taskService.listByProject(projectId);
        Map<String,List<TaskDto>> resultMap=new HashMap<>();
        for (Task task : taskList) {
            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();
            cal1.setTime(task.getEndTime());
            cal2.setTime(new Date());
            int res = hoursBetween(cal2.getTime(), cal1.getTime());
            String endTime = res / 24 + "d" + res % 24 + "h";
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setProjectId(task.getProjectId());
            taskDto.setUserId(task.getUserId());
            taskDto.setEndTime(endTime);
            if(res<0){
                taskDto.setOutOfTime("超期");
            }
            taskDto.setTitle(task.getTitle());
            taskDto.setStatus(task.getStatus());
            taskDto.setType(taskType[task.getType() - 1]);
            taskDto.setPriority(task.getPriority());
            taskDto.setBorder((borderColor[task.getPriority()-1]));
            if(task.getStatus().equals(4)){
                taskDto.setBorder(borderColor[borderColor.length-1]);
            }
            if(Strings.isNotEmpty(status)&&!status.equals("0")){
                if(task.getStatus()==Integer.parseInt(status)){
                    if(resultMap.get(task.getUserId())==null){
                        List<TaskDto> taskDtoList=new ArrayList<>();
                        taskDtoList.add(taskDto);
                        resultMap.put(task.getUserId(),taskDtoList);
                    }else{
                        resultMap.get(task.getUserId()).add(taskDto);
                    }
                }
            }else{
                if(resultMap.get(task.getUserId())==null){
                    List<TaskDto> taskDtoList=new ArrayList<>();
                    taskDtoList.add(taskDto);
                    resultMap.put(task.getUserId(),taskDtoList);
                }else{
                    resultMap.get(task.getUserId()).add(taskDto);
                }
            }
        }
        List<TaskUserDto> list=new ArrayList<>();
        for (Map.Entry<String,List<TaskDto>> entry : resultMap.entrySet()){
            TaskUserDto taskUserDto=new TaskUserDto();
            taskUserDto.setTaskDtoList(new ArrayList<>());
            taskUserDto.setUserId(entry.getKey());
            User user = userService.queryById(entry.getKey());
            ProjectUserRelation projectUserRelation=projectUserRelationService.getByUserId(user.getId(),projectId);
            taskUserDto.setUserName(user.getUserName());
            taskUserDto.setTaskDtoList(entry.getValue());
            taskUserDto.setRole(RoleEnum.RoleName[projectUserRelation.getRole()-1]);
            list.add(taskUserDto);
        }
        return list;
    }

    @Override
    public Project getByName(String projectName) {
        QueryWrapper<Project> projectQueryWrapper=new QueryWrapper<>();
        projectQueryWrapper.lambda().eq(Project::getName,projectName);
        Project project = projectDao.selectOne(projectQueryWrapper);
        return project;
    }

    @Override
    public void addDemand(AddDemandRequest addDemandRequest, MultipartFile[] fileList, String userId) {
        try {
            String []fileStringList=new String[fileList.length];
            int i=0;
            for (MultipartFile file : fileList) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadDir + file.getOriginalFilename());
                Path resultPath = Files.write(path, bytes);
                fileStringList[i]=resultPath.toString();
            }
            Demand demand=new Demand();
            Task task=new Task();
            demand.setCreateTime(new Date());
            demand.setModifyTime(new Date());
            demand.setId(UUID.randomUUID().toString());
            demand.setDetail(addDemandRequest.getDetail());
            demand.setTitle(addDemandRequest.getTitle());
            demand.setCreateUserId(userId);
            demand.setFileList(Arrays.toString(fileStringList));
            task.setCreateTime(new Date());
            task.setProjectId(addDemandRequest.getProjectId());
            task.setId(UUID.randomUUID().toString());
//            task.set
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
