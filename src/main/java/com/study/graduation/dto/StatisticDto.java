package com.study.graduation.dto;

import lombok.Data;

@Data
public class StatisticDto {
    float progressing;
    float overdue;
    float testing;
    float closed;
    float finished;
    float online;
}
