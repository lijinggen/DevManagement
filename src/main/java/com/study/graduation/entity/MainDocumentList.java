package com.study.graduation.entity;

import lombok.Data;

import java.util.List;

@Data
public class MainDocumentList {
    SubDocumentList demand;
    SubDocumentList stander;
    SubDocumentList dev;
    SubDocumentList other;
    boolean flag=true;
    String projectName;
    public MainDocumentList() {

    }
    public MainDocumentList(SubDocumentList demand,SubDocumentList stander, SubDocumentList dev, SubDocumentList other) {
        this.demand = demand;
        this.stander = stander;
        this.dev = dev;
        this.other = other;
    }
}
