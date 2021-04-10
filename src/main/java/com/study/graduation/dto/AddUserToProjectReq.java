package com.study.graduation.dto;

import lombok.Data;

@Data
public class AddUserToProjectReq {
    String userId;
    String projectId;
    int Role;
}
