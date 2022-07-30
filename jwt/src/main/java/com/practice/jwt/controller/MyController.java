package com.practice.jwt.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class MyController {

    @GetMapping("/welcome")
    public String welcome(){
        String text="This page is just private, ";
        text+="You cannot access this page .";

        return text;
    }
//    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/second")
    public String Second(){
        String text="Hey this is just testing.." +
                "......";
        return text;
    }
}
