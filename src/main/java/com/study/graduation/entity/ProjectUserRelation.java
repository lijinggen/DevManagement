package com.study.graduation.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ProjectUserRelation)实体类
 *
 * @author makejava
 * @since 2021-03-23 21:20:58
 */
public class ProjectUserRelation implements Serializable {
    private static final long serialVersionUID = 110334347869791069L;
    /**
     * 关系表id
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 角色
     */
    private int role;
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

    public Date getCreateTime() {
        return createTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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
