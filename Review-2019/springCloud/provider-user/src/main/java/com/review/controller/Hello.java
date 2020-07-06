package com.review.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "1  " + name;
    }
}
