package com.study.graduation.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Test)实体类
 *
 * @author makejava
 * @since 2021-05-04 17:13:04
 */
public class Test implements Serializable {
    private static final long serialVersionUID = -77877428109372852L;

    private String id;

    private String detail;

    private String taskId;
    /**
     * 关联开发单
     */
    private String relationTaskId;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRelationTaskId() {
        return relationTaskId;
    }

    public void setRelationTaskId(String relationTaskId) {
        this.relationTaskId = relationTaskId;
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
