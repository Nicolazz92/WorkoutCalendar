package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.ContestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contests")
public class ContestController {

    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @GetMapping("/{contestId}")
    public ModelAndView getUserByUserId(@PathVariable String contestId) {
        ModelAndView mav = new ModelAndView("contests/contest");
        mav.addObject("contest", contestService.getContestDTOById(Long.valueOf(contestId)));
        return mav;
    }
}
