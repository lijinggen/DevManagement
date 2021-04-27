package com.study.graduation.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.study.graduation.config.Result;
import com.study.graduation.dto.MessageDto;
import com.study.graduation.entity.Message;
import com.study.graduation.service.MessageService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2021-04-26 20:09:35
 */
@Controller
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Message selectOne(String id) {
        return this.messageService.queryById(id);
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) throws ParseException {
        String userId = (String)request.getSession().getAttribute("user_id");
        List<MessageDto> messages = messageService.queryAllByUserId(userId);
        model.addAttribute("message_list",messages);
        return "message";
    }

    @GetMapping("/read")
    @ResponseBody
    public Result<String> read(String id){
        messageService.read(id);
        return new Result(true);
    }
}
