package com.study.graduation.dto;

import lombok.Data;
import java.util.List;

@Data
public class TaskUserDto {
    String userName;
    String userId;
    String role;
    List<TaskDto> taskDtoList;
}
