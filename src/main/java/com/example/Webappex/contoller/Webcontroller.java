package com.example.Webappex.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Webcontroller {
    @GetMapping("/")
    public String test()
    {
        return "Hello";
    }
}
