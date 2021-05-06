package com.study.graduation.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {
    private String id;

    private String userId;

    private String projectId;

    private String title;

    private String type;

    private Integer status;

    private Integer priority;

    private String Border;

    private String endTime;

    private String outOfTime;

    private Date createTime;

    private Date modifyTime;

    private String projectName;
}
