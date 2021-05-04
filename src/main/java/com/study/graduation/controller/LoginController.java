package com.study.graduation.controller;

import com.study.graduation.config.Result;
import com.study.graduation.dto.RegisterDto;
import com.study.graduation.entity.User;
import com.study.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(Model model) {
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result<String> login(HttpServletRequest request, User user) {
        User userByAccount = userService.getByUserAccount(user.getUserAccount());
        if(userByAccount!=null){
            if (!userByAccount.getPassword().equals(user.getPassword())){
                return new Result(false);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("username",userByAccount.getUserName());
                session.setAttribute("useraccount",user.getUserAccount());
                session.setAttribute("user_id",userByAccount.getId());
                return new Result(true);
            }
        }
        return new Result(false);
    }

    @RequestMapping({"/home","/"})
    public String home(){
        return "home";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public Result<String> register(User user){
        User userServiceByUserAccount=userService.getByUserAccount(user.getUserAccount());
        if(userServiceByUserAccount!=null){
            return new Result(false);
        }else{
            user.setId(UUID.randomUUID().toString());
            user.setCreateTime(new Date());
            user.setModifyTime(new Date());
            user.setUserAccount(user.getUserAccount());
            user.setUserName(user.getUserName());
            userService.insert(user);
            return new Result(true);
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("user_id");
        request.getSession().removeAttribute("useraccount");
        return "login";
    }

}
