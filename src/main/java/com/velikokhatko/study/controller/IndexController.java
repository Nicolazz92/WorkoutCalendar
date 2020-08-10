package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.UserProfileService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final UserProfileService userProfileService;

    public IndexController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping({"", "/", "index", "index.html"})
    public String getUsers(Model model) {
        model.addAttribute("users", userProfileService.getUserProfileDTOs());
        return "index";
    }
}
