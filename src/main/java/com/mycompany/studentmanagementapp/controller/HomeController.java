package com.mycompany.studentmanagementapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String openSwaggerUI() {
        return "redirect:/swagger-ui.html";
    }
   @CrossOrigin
   @GetMapping("/display")
    public RedirectView display(){
        RedirectView redirectView=new RedirectView();
        redirectView.setUrl("http://localhost:8080/home.html");
        return redirectView;
}
}
