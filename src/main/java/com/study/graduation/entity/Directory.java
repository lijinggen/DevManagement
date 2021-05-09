package com.study.graduation.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Directory)实体类
 *
 * @author makejava
 * @since 2021-05-09 11:51:15
 */
public class Directory implements Serializable {
    private static final long serialVersionUID = -35432182362036348L;

    private String id;
    /**
     * 1. 需求文档 2. 规范文档 3.开发文档 4.其它
     */
    private Integer type;

    private String name;

    private String projectId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    private String createUser;

    private String desciption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

}
