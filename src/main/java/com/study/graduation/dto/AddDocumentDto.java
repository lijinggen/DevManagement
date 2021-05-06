package com.study.graduation.dto;

import lombok.Data;

@Data
public class AddDocumentDto {
    Integer type;
    String directoryId;
    String name;
    String projectId;
}
