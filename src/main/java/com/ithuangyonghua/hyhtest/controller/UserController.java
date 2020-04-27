package com.ithuangyonghua.hyhtest.controller;

import com.ithuangyonghua.hyhtest.comments.AjaxResult;
import com.ithuangyonghua.hyhtest.dao.EmployeeDao;
import com.ithuangyonghua.hyhtest.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class UserController {
    
    @Autowired
    EmployeeDao employeeDao;

    //@PutMapping:处理put请求
    //@DeleteMapping
    //@GetMapping
    @PostMapping(value="/user/login")
    public String login(@RequestParam("username")  String username,
                        @RequestParam("password") String password, Map<String,Object> map, HttpServletRequest request){
        if(username.equals("admin")&&password.equals("password")){
            //登陆成功
            //防止表单重复提交,可以重定向到主页
            HttpSession session = request.getSession();
            session.setAttribute("loginUser","username");
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名密码错误");
            return "index";
        }

    }
    @GetMapping(value="/emps")
    public String getEmps(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        return "list";
    }
}
