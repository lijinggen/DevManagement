package com.study.graduation.dto;

import lombok.Data;

@Data
public class AddBugRequest {
    private String detail;//详情
    private String userId;//开发人员
    private String relationTaskId;//关联开发任务
    private String projectId;//项目id
    private String priority;//优先级
    private String beginTime;//开始时间
    private String endTime;//结束时间
}
