package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.PerformanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/{performanceId}")
    public ModelAndView getUserByUserId(@PathVariable String performanceId) {
        ModelAndView mav = new ModelAndView("performance/performance");
        mav.addObject("performance", performanceService.getPerformanceDTOById(Long.valueOf(performanceId)));
        return mav;
    }
}
