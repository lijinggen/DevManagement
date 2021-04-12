package com.study.graduation.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Task)实体类
 *
 * @author makejava
 * @since 2021-04-11 16:28:44
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 877477560874525693L;

    private String id;

    private String userId;

    private String projectId;

    private String title;
    /**
     * 1. 需求 2.测试 3. bug
     */
    private Integer type;
    /**
     * 1. 进行中 2.已完成 3. 已上线 4. 已关闭
     */
    private Integer status;
    /**
     * 1. low 2. middle 3. high
     */
    private Integer priority;
    /**
     * 任务预期完成时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
