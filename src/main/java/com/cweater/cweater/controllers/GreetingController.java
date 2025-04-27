package com.cweater.cweater.controllers;

import com.cweater.cweater.config.OwnUserDetail;
import com.cweater.cweater.service.GreetingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    private final GreetingService service;
    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        return service.main(model);
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal OwnUserDetail userDetail,
            @RequestParam String text,
            @RequestParam(defaultValue = "new") String tag,
            Map<String, Object> model
    ) {
        return service.add(userDetail, text, tag, model);
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        return service.filter(filter, model);
    }
}
