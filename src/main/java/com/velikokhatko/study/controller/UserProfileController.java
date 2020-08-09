package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.UserProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {

    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping({"", "/", "index", "index.html"})
    public String getReducedUsers(Model model) {
        model.addAttribute("users", userProfileService.getUserProfileDTOs());
        return "index";
    }
}
