package com.study.graduation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ListProjectReq implements Serializable {
    private String UserId;
}
