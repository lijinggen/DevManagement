package com.study.graduation.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private String id;

    private String project;

    private String fromUser;

    private String toUser;

    private Integer isRead;

    private String title;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;

    private String toUserId;
}