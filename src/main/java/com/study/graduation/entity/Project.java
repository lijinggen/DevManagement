package com.study.graduation.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Project)实体类
 *
 * @author makejava
 * @since 2021-03-23 21:17:05
 */
@Data
public class Project implements Serializable {
    private static final long serialVersionUID = 137611330148320864L;
    /**
     * 项目id
     */
    private String id;
    /**
     * 项目名字
     */
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
