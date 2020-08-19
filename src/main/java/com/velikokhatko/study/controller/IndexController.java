package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.ContestService;
import com.velikokhatko.study.service.TrackService;
import com.velikokhatko.study.service.UserProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final UserProfileService userProfileService;
    private final ContestService contestService;
    private final TrackService trackService;

    public IndexController(UserProfileService userProfileService,
                           ContestService contestService,
                           TrackService trackService) {
        this.userProfileService = userProfileService;
        this.contestService = contestService;
        this.trackService = trackService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getUsers(Model model) {
        model.addAttribute("users", userProfileService.getUserProfileDTOs("name"));
        model.addAttribute("contests", contestService.getContestDTOs("name"));
        model.addAttribute("tracks", trackService.getTrackDTOs("name"));
        return "index";
    }
}
