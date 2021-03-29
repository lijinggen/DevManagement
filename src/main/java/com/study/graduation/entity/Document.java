package com.study.graduation.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Document)实体类
 *
 * @author makejava
 * @since 2021-03-29 13:08:54
 */
public class Document implements Serializable {
    private static final long serialVersionUID = -98690824553668611L;

    private String id;
    /**
     * 1. 需求文档 2. 规范文档 3.开发文档 4.其它
     */
    private Integer type;

    private String name;

    private String path;

    private String projectId;

    private String directoryId;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(String directoryId) {
        this.directoryId = directoryId;
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
