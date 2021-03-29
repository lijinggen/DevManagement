package com.study.graduation.entity;

import lombok.Data;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubDocumentList {
    List<DirectoryAndDocList> directories;
    List<Document> fileList;

    public SubDocumentList() {
        directories=new ArrayList<>();
        fileList=new ArrayList<>();
    }
}
