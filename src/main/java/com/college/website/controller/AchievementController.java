package com.college.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AchievementController {

    @GetMapping("/achievement-2021")
    public String achievement2021() {
        return "achievement-2021";
    }

    @GetMapping("/achievement-2022")
    public String achievement2022() {
        return "achievement-2022";
    }

}