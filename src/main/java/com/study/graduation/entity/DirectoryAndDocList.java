package com.study.graduation.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DirectoryAndDocList {
    Directory directory;
    List<Document> fileList;

    public DirectoryAndDocList() {
        fileList=new ArrayList<>();
    }
}
