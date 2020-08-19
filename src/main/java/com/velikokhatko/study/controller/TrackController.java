package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/{trackId}")
    public ModelAndView getUserByUserId(@PathVariable String trackId) {
        ModelAndView mav = new ModelAndView("tracks/track");
        mav.addObject("track", trackService.getTrackDTOById(Long.valueOf(trackId)));
        return mav;
    }
}
