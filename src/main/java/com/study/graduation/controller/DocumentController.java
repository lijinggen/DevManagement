package com.study.graduation.controller;

import com.study.graduation.config.Result;
import com.study.graduation.dto.AddDocumentDto;
import com.study.graduation.entity.MainDocumentList;
import com.study.graduation.entity.Project;
import com.study.graduation.entity.User;
import com.study.graduation.service.DirectoryService;
import com.study.graduation.service.DocumentService;
import com.study.graduation.service.ProjectService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.study.graduation.dto.ListProjectReq;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    ProjectService projectService;

    @Resource
    private DirectoryService directoryService;

    @Resource
    private DocumentService documentService;

    @RequestMapping("/document")
    public String document(Model model, HttpServletRequest request, String id) {
        String userId = (String) request.getSession().getAttribute("user_id");
        ListProjectReq listProjectReq = new ListProjectReq();
        listProjectReq.setUserId(userId);
        List<Project> list = projectService.list(listProjectReq);
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Project project : list) {
                Map<String, Object> item = new HashMap<>();
                item.put("id", project.getId());
                item.put("name", project.getName());
                resultList.add(item);
            }
        }
        model.addAttribute("project_list", resultList);
        if (Strings.isNotBlank(id)) {
            MainDocumentList mainDocumentList = directoryService.listByProject(id);
            model.addAttribute("project_name", mainDocumentList.getProjectName());
            model.addAttribute("demand_dirs", mainDocumentList.getDemand().getDirectories());
            model.addAttribute("demand_files", mainDocumentList.getDemand().getFileList());
            model.addAttribute("stander_dirs", mainDocumentList.getStander().getDirectories());
            model.addAttribute("stander_files", mainDocumentList.getStander().getFileList());
            model.addAttribute("dev_dirs", mainDocumentList.getDev().getDirectories());
            model.addAttribute("dev_files", mainDocumentList.getDev().getFileList());
            model.addAttribute("other_dirs", mainDocumentList.getOther().getDirectories());
            model.addAttribute("other_files", mainDocumentList.getOther().getFileList());
        }
        return "document";
    }

    @PostMapping("/addFile")
    public String addFile(AddDocumentDto addDocumentDto, MultipartFile file) {
        System.out.println(addDocumentDto);
        return "redirect:document?id=" + addDocumentDto.getProjectId();
    }
}
