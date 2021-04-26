package com.study.graduation.dto;

import lombok.Data;

@Data
public class AddDemandRequest {
    private String title;//标题
    private String detail;//详情

    private String userId;//开发人员
    private String projectId;//项目id
    private String priority;//优先级
    private String beginTime;//开始事件
    private String endTime;//结束时间
}
