package com.mycompany.studentmanagementapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping("/swagger")
    public String openSwaggerUI() {
        return "redirect:/swagger-ui.html";
    }


    @GetMapping("/")
    public RedirectView display() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://www.campusplacehub.com/home");
        return redirectView;
    }

}
