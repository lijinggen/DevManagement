package com.study.graduation.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BugDetailDto {
    private String id;

    private String taskId;

    private String relationTaskId;

    private String detauk;

    private String batchNo;

    private String userId;

    private String projectId;

    private String title;

    private String type;

    private String status;

    private String priority;

    private String beginTime;

    private String endTime;

    private String createUser;

    private String createTime;

    private String modifyTime;
}
