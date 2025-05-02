package com.cweater.cweater.controllers;

import com.cweater.cweater.entities.User;
import com.cweater.cweater.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {

    private final RegistrationService service;
    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            User user,
            Map<String, Object> model) {
        return service.addUser(user, model);
    }

    @GetMapping("/activate/{code}")
    public String activate(Map<String, Object> model, @PathVariable String code) {
        return service.activate(model, code);
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String authentication(User user, Map<String, Object> model) {
//        return service.addUser(user, model);
//    }

}
