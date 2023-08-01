package com.codecool.stackoverflowtw.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class IndexController {

    @GetMapping
    public String welcome() {
        return "Hello!";
    }


}

