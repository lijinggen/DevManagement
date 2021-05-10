package com.study.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.graduation.dao.*;
import com.study.graduation.dto.*;
import com.study.graduation.entity.*;
import com.study.graduation.service.*;
import com.study.graduation.util.DateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private DemandService demandService;

    @Resource
    private TaskService taskService;

    @Resource
    private TaskDao taskDao;

    @Resource
    private BugDao bugDao;

    @Resource
    private MessageService messageService;

    @Resource
    private TestService testService;

    @Resource
    private UserService userService;

    @Resource
    private DemandDao demandDao;

    @Resource
    private TestDao testDao;

    @Resource
    private ProjectService projectService;

    @Resource
    private BugService bugService;

    @Value("${customFile}")
    public String uploadDir;

    private static String[] taskType = {"需求", "测试", "bug"};

    private static String[] borderColor = {"border-left: 4px solid #DC3545;", "border-left: 4px solid #FFC107;",
            "border-left: 4px solid #28A745;", "border-left: 4px solid #6C7586;"};

    private static String status[] = {"进行中", "已完成", "已上线", "已关闭", "开发修复中", "待上线"};

    private static String priority[] = {"low", "middle", "high"};

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
        QueryWrapper<ProjectUserRelation> queryWrapper = new QueryWrapper();
        List<Project> result = new ArrayList<>();
        if (Strings.isNotEmpty(req.getUserId())) {
            queryWrapper.lambda().eq(ProjectUserRelation::getUserId, req.getUserId());
            if (req.getRole() != 0) {
                queryWrapper.lambda().eq(ProjectUserRelation::getRole, req.getRole());
            }
            List<ProjectUserRelation> projectUserRelations = projectUserRelationDao.selectList(queryWrapper);
            if (projectUserRelations != null) {
                QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
                List<String> ids = projectUserRelations.stream().map(ProjectUserRelation::getProjectId).collect(Collectors.toList());
                projectQueryWrapper.lambda().in(Project::getId, ids);
                if (ids != null && ids.size() > 0) {
                    List<Project> projects = projectDao.selectList(projectQueryWrapper);
                    return projects;
                }
                return null;
            } else {
                return result;
            }
        } else {
            return null;
        }
    }

    public int hoursBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60));
    }

    @Override
    public List<TaskUserDto> listTask(String projectId, String status) throws ParseException {
        List<Task> taskList = taskService.listByProject(projectId);
        Map<String, List<TaskDto>> resultMap = new HashMap<>();
        for (Task task : taskList) {
            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();
            cal1.setTime(task.getEndTime());
            cal2.setTime(new Date());
            int res = hoursBetween(cal2.getTime(), cal1.getTime());
            String endTime = res / 24 + "天" + res % 24 + "小时";
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setProjectId(task.getProjectId());
            taskDto.setUserId(task.getUserId());
            if (task.getStatus().equals(1) || task.getStatus().equals(5)) {
                taskDto.setEndTime(endTime);
                if (res < 0) {
                    taskDto.setOutOfTime("超期");
                }
            }
            taskDto.setTitle(task.getTitle());
            taskDto.setStatus(task.getStatus());
            taskDto.setType(taskType[task.getType() - 1]);
            taskDto.setPriority(task.getPriority());
            taskDto.setBorder((borderColor[task.getPriority() - 1]));
            if (task.getStatus().equals(4)) {
                taskDto.setBorder(borderColor[borderColor.length - 1]);
            }
            if (Strings.isNotEmpty(status) && !status.equals("0")) {
                if (task.getStatus() == Integer.parseInt(status)) {
                    if (resultMap.get(task.getUserId()) == null) {
                        List<TaskDto> taskDtoList = new ArrayList<>();
                        taskDtoList.add(taskDto);
                        resultMap.put(task.getUserId(), taskDtoList);
                    } else {
                        resultMap.get(task.getUserId()).add(taskDto);
                    }
                }
            } else {
                if (resultMap.get(task.getUserId()) == null) {
                    List<TaskDto> taskDtoList = new ArrayList<>();
                    taskDtoList.add(taskDto);
                    resultMap.put(task.getUserId(), taskDtoList);
                } else {
                    resultMap.get(task.getUserId()).add(taskDto);
                }
            }
        }
        List<TaskUserDto> list = new ArrayList<>();
        for (Map.Entry<String, List<TaskDto>> entry : resultMap.entrySet()) {
            TaskUserDto taskUserDto = new TaskUserDto();
            taskUserDto.setTaskDtoList(new ArrayList<>());
            taskUserDto.setUserId(entry.getKey());
            User user = userService.queryById(entry.getKey());
            ProjectUserRelation projectUserRelation = projectUserRelationService.getByUserId(user.getId(), projectId);
            taskUserDto.setUserName(user.getUserName());
            taskUserDto.setTaskDtoList(entry.getValue());
            taskUserDto.setRole(RoleEnum.RoleName[projectUserRelation.getRole() - 1]);
            list.add(taskUserDto);
        }
        return list;
    }

    @Override
    public Project getByName(String projectName) {
        QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
        projectQueryWrapper.lambda().eq(Project::getName, projectName);
        Project project = projectDao.selectOne(projectQueryWrapper);
        return project;
    }

    @Override
    public void addDemand(AddDemandRequest addDemandRequest, MultipartFile[] fileList, String userId) {
        try {
            String[] fileNameList = new String[fileList.length];
            String res = "";
            int i = 0;
            for (MultipartFile file : fileList) {
                String uuid = UUID.randomUUID().toString();
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadDir + uuid + "~-" + file.getOriginalFilename());
                Files.write(path, bytes);
                fileNameList[i] = "/graduation/" + uuid + "~-" + file.getOriginalFilename();
                i++;
            }
            for (int i1 = 0; i1 < fileNameList.length; i1++) {
                res += fileNameList[i1];
                if (i1 + 1 != fileNameList.length) {
                    res += ",";
                }
            }
            Demand demand = new Demand();
            Task task = new Task();
            demand.setCreateTime(new Date());
            demand.setModifyTime(new Date());
            demand.setId(UUID.randomUUID().toString());
            demand.setDetail(addDemandRequest.getDetail());
            demand.setTitle(addDemandRequest.getTitle());
            demand.setCreateUserId(userId);
            demand.setFileList(res);
            task.setCreateTime(new Date());
            task.setModifyTime(new Date());
            task.setProjectId(addDemandRequest.getProjectId());
            task.setId(UUID.randomUUID().toString());
            task.setBatchNo(UUID.randomUUID().toString());
            task.setBeginTime(DateUtil.prase(addDemandRequest.getBeginTime()));
            task.setEndTime(DateUtil.prase(addDemandRequest.getEndTime()));
            task.setTitle(addDemandRequest.getTitle());
            task.setUserId(addDemandRequest.getUserId());
            task.setType(1);
            task.setStatus(1);
            task.setCreateUser(userId);
            demand.setTaskId(task.getId());
            if (addDemandRequest.getPriority().equals("高")) {
                task.setPriority(1);
            } else if (addDemandRequest.getPriority().equals("中")) {
                task.setPriority(2);
            } else if (addDemandRequest.getPriority().equals("低")) {
                task.setPriority(3);
            }
            Message message = new Message();
            message.setId(UUID.randomUUID().toString());
            message.setCreateTime(new Date());
            message.setModifyTime(new Date());
            message.setFromUser(userService.queryById(userId).getUserName());
            message.setToUser(userService.queryById(addDemandRequest.getUserId()).getUserName());
            message.setTitle(addDemandRequest.getTitle());
            message.setToUserId(addDemandRequest.getUserId());
            message.setIsRead(0);
            message.setProject(projectDao.selectById(addDemandRequest.getProjectId()).getName());
            messageService.insert(message);
            demandService.insert(demand);
            taskService.insert(task);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTest(AddTestRequest addTestRequest, String userId) throws ParseException {
        Test test=new Test();
        Task task=new Task();
        Task devTask=taskService.queryById(addTestRequest.getRelationTaskId());
        task.setCreateUser(userId);
        task.setModifyTime(new Date());
        task.setCreateTime(new Date());
        task.setBatchNo(devTask.getBatchNo());
        if (addTestRequest.getPriority().equals("高")) {
            task.setPriority(1);
        } else if (addTestRequest.getPriority().equals("中")) {
            task.setPriority(2);
        } else if (addTestRequest.getPriority().equals("低")) {
            task.setPriority(3);
        }
        task.setBeginTime(DateUtil.prase(addTestRequest.getBeginTime()));
        task.setEndTime(DateUtil.prase(addTestRequest.getEndTime()));
        task.setId(UUID.randomUUID().toString());
        task.setStatus(1);
        task.setTitle("【测试】"+taskService.queryById(addTestRequest.getRelationTaskId()).getTitle());
        task.setType(2);
        task.setProjectId(addTestRequest.getProjectId());
        task.setUserId(addTestRequest.getUserId());

        test.setId(UUID.randomUUID().toString());
        test.setCreateTime(new Date());
        test.setModifyTime(new Date());
        test.setTaskId(task.getId());
        test.setRelationTaskId(addTestRequest.getRelationTaskId());
        test.setDetail(addTestRequest.getDetail());
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setCreateTime(new Date());
        message.setModifyTime(new Date());
        message.setFromUser(userService.queryById(userId).getUserName());
        message.setToUser(userService.queryById(addTestRequest.getUserId()).getUserName());
        message.setTitle("分配了一个任务给您【测试】"+taskService.queryById(addTestRequest.getRelationTaskId()).getTitle());
        message.setToUserId(addTestRequest.getUserId());
        message.setIsRead(0);
        message.setProject(projectDao.selectById(addTestRequest.getProjectId()).getName());
        messageService.insert(message);
        testService.insert(test);
        taskService.insert(task);
    }

    @Override
    public void addBug(AddBugRequest addBugRequest, String userId) throws ParseException {
        // userId当前用户
        Bug bug=new Bug();
        Task task=new Task();
        Task testTask=taskService.queryById(addBugRequest.getRelationTaskId());
        task.setCreateUser(userId);
        task.setModifyTime(new Date());
        task.setCreateTime(new Date());
        task.setBatchNo(testTask.getBatchNo());
        if (addBugRequest.getPriority().equals("高")) {
            task.setPriority(1);
        } else if (addBugRequest.getPriority().equals("中")) {
            task.setPriority(2);
        } else if (addBugRequest.getPriority().equals("低")) {
            task.setPriority(3);
        }
        task.setBeginTime(DateUtil.prase(addBugRequest.getBeginTime()));
        task.setEndTime(DateUtil.prase(addBugRequest.getEndTime()));
        task.setId(UUID.randomUUID().toString());
        task.setStatus(1);
        task.setTitle("【BUG】"+taskService.queryById(addBugRequest.getRelationTaskId()).getTitle());
        task.setType(3);
        task.setProjectId(addBugRequest.getProjectId());
        task.setUserId(addBugRequest.getUserId());

        bug.setCreateTime(new Date());
        bug.setModifyTime(new Date());
        bug.setId(UUID.randomUUID().toString());
        bug.setTaskId(task.getId());
        bug.setRelationTaskId(addBugRequest.getRelationTaskId());
        bug.setDetauk(addBugRequest.getDetail());

        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setCreateTime(new Date());
        message.setModifyTime(new Date());
        message.setFromUser(userService.queryById(userId).getUserName());
        message.setToUser(userService.queryById(addBugRequest.getUserId()).getUserName());
        message.setTitle("分配了一个任务给您【BUG】"+taskService.queryById(addBugRequest.getRelationTaskId()).getTitle());
        message.setToUserId(addBugRequest.getUserId());
        message.setIsRead(0);
        message.setProject(projectDao.selectById(addBugRequest.getProjectId()).getName());
        messageService.insert(message);

        testTask.setStatus(5);
        taskService.update(testTask);

        taskService.insert(task);

        bugService.insert(bug);
    }

    @Override
    public StatisticDto statistic(String userId) throws ParseException {
        ListTaskReq listTaskReq = new ListTaskReq();
        listTaskReq.setUserId(userId);
        List<Task> list = taskService.list(listTaskReq);
        StatisticDto statisticDto = new StatisticDto();
        listTaskReq.setUserId("");
        Map<String, Task> map = new HashMap<>();
        if (list != null) {
            for (Task task : list) {
                map.putIfAbsent(task.getBatchNo(), task);
            }
            for (Map.Entry<String, Task> kv : map.entrySet()) {
                listTaskReq.setBatchNo(kv.getKey());
                // 根据批次获取到该需求的所有关联单
                List<Task> list1 = taskService.list(listTaskReq);
                for (Task task : list1) {
                    if (task.getUserId().equals(userId)) {
                        //status 1. 进行中 2.已完成 3. 已上线 4. 已关闭 5.开发修复中 6. 待上线
                        if (task.getStatus().equals(1) || task.getStatus().equals(5)) {
                            Calendar cal1 = new GregorianCalendar();
                            Calendar cal2 = new GregorianCalendar();
                            cal1.setTime(task.getEndTime());
                            cal2.setTime(new Date());
                            int res = hoursBetween(cal2.getTime(), cal1.getTime());
                            if (res < 0) {
                                statisticDto.setOverdue(statisticDto.getOverdue() + 1);
                            } else {
                                statisticDto.setProgressing(statisticDto.getProgressing() + 1);
                            }
                        } else if (task.getStatus().equals(2) || task.getStatus().equals(3) || task.getStatus().equals(4) || task.getStatus().equals(6)) {
                            statisticDto.setFinished(statisticDto.getFinished() + 1);
                        }

                        if (task.getType().equals(1)) {
                            statisticDto.setDev(statisticDto.getDev() + 1);
                        } else if (task.getType().equals(2)) {
                            statisticDto.setTest(statisticDto.getTest() + 1);
                        } else if (task.getType().equals(3)) {
                            statisticDto.setBug(statisticDto.getBug() + 1);
                        }
                    } else {
                        if (task.getType().equals(2)) {
                            if (task.getStatus().equals(1)||task.getStatus().equals(5)) {
                                statisticDto.setTesting(statisticDto.getTesting() + 1);
                            }
                        }
                    }

                }
            }
        }
        statisticDto.setICreate(taskService.getICreate(userId));
        StatisticDto statisticDto1 = statisticProjectProgress(userId);
        statisticDto.setWeiWanCheng(statisticDto1.getWeiWanCheng());
        statisticDto.setWanCheng(statisticDto1.getWanCheng());
        statisticDto.setProject(statisticDto1.getProject());
        return statisticDto;
    }

    @Override
    public StatisticDto statisticProjectProgress(String userId) {
        StatisticDto statisticDto = new StatisticDto();
        QueryWrapper<ProjectUserRelation> projectUserRelationQueryWrapper = new QueryWrapper<>();
        projectUserRelationQueryWrapper.lambda().eq(ProjectUserRelation::getUserId, userId);
        List<ProjectUserRelation> projectUserRelations = projectUserRelationDao.selectList(projectUserRelationQueryWrapper);
        statisticDto.setWanCheng(new int[projectUserRelations.size()]);
        statisticDto.setWeiWanCheng(new int[projectUserRelations.size()]);
        statisticDto.setProject(new String[projectUserRelations.size()]);
        int i = 0;
        for (int i1 = projectUserRelations.size() - 1; i1 >= 0; i1--) {
            Project project = projectService.queryById(projectUserRelations.get(i1).getProjectId());
            statisticDto.getProject()[i1] = project.getName();
            List<Task> tasks = taskService.listByProject(project.getId());
            for (Task task : tasks) {
                if (task.getStatus().equals(1) || task.getStatus().equals(5)) {
                    statisticDto.getWanCheng()[i1]++;
                } else {
                    statisticDto.getWeiWanCheng()[i1]++;
                }
            }
        }
        return statisticDto;
    }

    @Override
    public Integer getRole(String userId) {
        QueryWrapper<ProjectUserRelation> projectUserRelationQueryWrapper = new QueryWrapper<>();
        projectUserRelationQueryWrapper.lambda().eq(ProjectUserRelation::getUserId, userId);
        ProjectUserRelation project = projectUserRelationDao.selectOne(projectUserRelationQueryWrapper);
        return project.getRole();
    }

    @Override
    public List<DemandDetailDto> listDemandDetail(String projectId) throws ParseException {
        List<Task> tasks = taskService.listByProject(projectId);
        List<DemandDetailDto> list = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getType()==1){
                QueryWrapper<Demand> demandQueryWrapper = new QueryWrapper<>();
                demandQueryWrapper.lambda().eq(Demand::getTaskId, task.getId());
                Demand demand = demandDao.selectOne(demandQueryWrapper);
                DemandDetailDto demandDetailDto = new DemandDetailDto();
                demandDetailDto.setId(task.getId());
                demandDetailDto.setBatchNo(task.getBatchNo());
                demandDetailDto.setBeginTime(DateUtil.format(task.getBeginTime()));
                demandDetailDto.setEndTime(DateUtil.format(task.getEndTime()));
                demandDetailDto.setStatus(status[task.getStatus() - 1]);
                demandDetailDto.setType(taskType[task.getType() - 1]);
                demandDetailDto.setPriority(priority[task.getPriority() - 1]);
                demandDetailDto.setTitle(task.getTitle());
                demandDetailDto.setUserId(task.getUserId());
                demandDetailDto.setCreateUser(userService.queryById(task.getCreateUser()).getUserName());
                demandDetailDto.setDetail(demand.getDetail());
                String[] split = demand.getFileList().split(",");
                FileItemDto fileItemDto[] = new FileItemDto[split.length];
                int i = 0;
                for (String s : split) {
                    fileItemDto[i] = new FileItemDto();
                    fileItemDto[i].setPath(s);
                    String[] split1 = s.split("~-");
                    fileItemDto[i].setName(split1[split1.length - 1]);
                    i++;
                }
                demandDetailDto.setFileList(fileItemDto);
                demandDetailDto.setDemandId(demand.getId());
                list.add(demandDetailDto);
            }
        }
        return list;
    }

    @Override
    public List<TestDetailDto> listTestDetail(String projectId) throws ParseException {
        List<Task> tasks = taskService.listByProject(projectId);
        List<TestDetailDto> list = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getType()==2){
                QueryWrapper<Test> demandQueryWrapper = new QueryWrapper<>();
                demandQueryWrapper.lambda().eq(Test::getTaskId, task.getId());
                Test test = testDao.selectOne(demandQueryWrapper);
                TestDetailDto testDetailDto = new TestDetailDto();
                testDetailDto.setId(task.getId());
                testDetailDto.setBatchNo(task.getBatchNo());
                testDetailDto.setBeginTime(DateUtil.format(task.getBeginTime()));
                testDetailDto.setEndTime(DateUtil.format(task.getEndTime()));
                testDetailDto.setStatus(status[task.getStatus() - 1]);
                testDetailDto.setType(taskType[task.getType() - 1]);
                testDetailDto.setPriority(priority[task.getPriority() - 1]);
                testDetailDto.setTitle(task.getTitle());
                testDetailDto.setUserId(task.getUserId());
                testDetailDto.setCreateUser(userService.queryById(task.getCreateUser()).getUserName());
                testDetailDto.setDetail(test.getDetail());
                testDetailDto.setTestId(test.getId());
                list.add(testDetailDto);
            }
        }
        return list;
    }

    @Override
    public List<BugDetailDto> listBugDetail(String projectId) throws ParseException {
        List<Task> tasks = taskService.listByProject(projectId);
        List<BugDetailDto> list = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getType()==3){
                QueryWrapper<Bug> demandQueryWrapper = new QueryWrapper<>();
                demandQueryWrapper.lambda().eq(Bug::getTaskId, task.getId());
                Bug bug = bugDao.selectOne(demandQueryWrapper);
                BugDetailDto bugDetailDto = new BugDetailDto();
                bugDetailDto.setId(task.getId());
                bugDetailDto.setBatchNo(task.getBatchNo());
                bugDetailDto.setBeginTime(DateUtil.format(task.getBeginTime()));
                bugDetailDto.setEndTime(DateUtil.format(task.getEndTime()));
                bugDetailDto.setStatus(status[task.getStatus() - 1]);
                bugDetailDto.setType(taskType[task.getType() - 1]);
                bugDetailDto.setPriority(priority[task.getPriority() - 1]);
                bugDetailDto.setTitle(task.getTitle());
                bugDetailDto.setUserId(task.getUserId());
                bugDetailDto.setCreateUser(userService.queryById(task.getCreateUser()).getUserName());
                bugDetailDto.setDetauk(bug.getDetauk());
                list.add(bugDetailDto);
            }
        }
        return list;
    }
}
