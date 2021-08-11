package com.ahmadsolehin.SUSAHCRUDNIE.controller;


import com.ahmadsolehin.SUSAHCRUDNIE.model.User;
import com.ahmadsolehin.SUSAHCRUDNIE.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahmadsolehin.SUSAHCRUDNIE.model.Student;
import com.ahmadsolehin.SUSAHCRUDNIE.service.StudentService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public String home(Model model,HttpSession httpSession) {
        model.addAttribute("students", studentService.findAll());
        if(httpSession.getAttribute("isLogin")==null)
        httpSession.setAttribute("isLogin",false);
        return "home";
    }

    @GetMapping("/adminLogin")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/student/login")
    public String validate(@Valid User user, BindingResult result, RedirectAttributes redirect, HttpSession httpSession) {
        if (result.hasErrors()) {
            return "login";
        }
        boolean flag=userService.validate(user.getEmail(),user.getPassword());
        httpSession.setAttribute("isLogin",flag);
        if(flag)
        redirect.addFlashAttribute("success", "login success ");
        else
            redirect.addFlashAttribute("success", "fail");
        return "redirect:/student";
    }

    @GetMapping("/student")
    public String index(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "list";
    }

    @GetMapping("/student/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @GetMapping("/student/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.findByStudentId(id));
        return "form";
    }

    @PostMapping("/student/save")
    public String save(@Valid Student employee, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        studentService.save(employee);
        redirect.addFlashAttribute("success", "Saved student details successfully!");
        return "redirect:/student";
    }

    @GetMapping("/student/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        studentService.deleteById(id);
        redirect.addFlashAttribute("success", "Deleted student successfully!");
        return "redirect:/student";
    }

    @GetMapping("/student/search")
    public String search(@RequestParam("s") String s, Model model) {
        if (s.equals("")) {
            return "redirect:/student";
        }

        model.addAttribute("students", studentService.search(s));
        return "list";
    }
}