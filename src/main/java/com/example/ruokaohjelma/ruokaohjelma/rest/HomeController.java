package com.example.ruokaohjelma.ruokaohjelma.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @PostMapping("/submit")
    public void onSubmit(HttpServletRequest httpRequest){
        String string = httpRequest.getParameter("nimi");
        System.out.println(string);
    }

}
