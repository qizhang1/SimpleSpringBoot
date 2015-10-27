package com.example;

import org.springframework.web.bind.annotation.*;

// combines @Controller and @ResponseBody
@RestController
public class HelloController {

    @RequestMapping("/")
    public String home() {
        return "Hello Spring Boot!";
    }
}
