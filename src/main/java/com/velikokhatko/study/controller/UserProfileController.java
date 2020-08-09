package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.UserProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("{userId}")
    public String getUserByUserId(Model model, @PathVariable String userId) {
        model.addAttribute("user", userProfileService.getUserProfileDTOById(Long.valueOf(userId)));
        return "index";
    }
}
