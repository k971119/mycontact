package com.fastcampus.javaallinone.project3.mycontact.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(value = "api/helloWorld")
    public String helloWorld(){
        return "HelloWorld";
    }
}
