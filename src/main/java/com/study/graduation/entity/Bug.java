package com.study.graduation.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Bug)实体类
 *
 * @author makejava
 * @since 2021-05-07 21:10:15
 */
public class Bug implements Serializable {
    private static final long serialVersionUID = 511205896901681349L;

    private String id;

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
    /**
     * bug描述
     */
    private String detauk;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDetauk() {
        return detauk;
    }

    public void setDetauk(String detauk) {
        this.detauk = detauk;
    }

}
