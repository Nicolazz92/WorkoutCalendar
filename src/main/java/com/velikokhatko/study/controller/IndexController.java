package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.ContestService;
import com.velikokhatko.study.service.UserProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final UserProfileService userProfileService;
    private final ContestService contestService;

    public IndexController(UserProfileService userProfileService,
                           ContestService contestService) {
        this.userProfileService = userProfileService;
        this.contestService = contestService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getUsers(Model model) {
        model.addAttribute("users", userProfileService.getUserProfileDTOs());
        model.addAttribute("contests", contestService.getContestDTOs("name"));
        return "index";
    }
}
