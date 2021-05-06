package com.study.graduation.controller;

import com.study.graduation.entity.Directory;
import com.study.graduation.entity.MainDocumentList;
import com.study.graduation.service.DirectoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Directory)表控制层
 *
 * @author makejava
 * @since 2021-03-24 21:36:12
 */
@Controller
@RequestMapping("directory")
public class DirectoryController {
    /**
     * 服务对象
     */
    @Resource
    private DirectoryService directoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Directory selectOne(String id) {
        return this.directoryService.queryById(id);
    }

    @RequestMapping("listByProject")
    public String listByProject(Model model ,String id){
        MainDocumentList mainDocumentList = directoryService.listByProject(id);
        model.addAttribute("project_name",mainDocumentList.getProjectName());
        model.addAttribute("project_id",id);
        return "document";
    }
}
