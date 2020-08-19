package com.velikokhatko.study.controller;

import com.velikokhatko.study.model.Track;
import com.velikokhatko.study.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tracks")
public class TrackController {
    private static final String VIEW_TRACK = "tracks/track";
    private static final String CREATE_OR_UPDATE_TRACK = "tracks/createOrUpdateTrack";

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{trackId}")
    public ModelAndView getUserByUserId(@PathVariable Long trackId) {
        ModelAndView mav = new ModelAndView(VIEW_TRACK);
        mav.addObject("track", trackService.getTrackDTOById(trackId));
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView initCreationForm() {
        ModelAndView mav = new ModelAndView(CREATE_OR_UPDATE_TRACK);
        mav.addObject("track", Track.builder().build());
        return mav;
    }

    @PostMapping("/new")
    public String processCreateTrackForm(Track owner) {
        Track savedTrack = trackService.save(owner);
        return "redirect:/tracks/" + savedTrack.getId();
    }

    @GetMapping("/{trackId}/edit")
    public ModelAndView initCreateOrUpdateTrackForm(@PathVariable Long trackId) {
        ModelAndView mav = new ModelAndView(CREATE_OR_UPDATE_TRACK);
        mav.addObject("track", trackService.getTrackById(trackId));
        return mav;
    }

    @PostMapping("/{trackId}/edit")
    public String processCreateOrUpdateTrackForm(Track track, @PathVariable Long trackId) {
        track.setId(trackId);
        Track savedTrack = trackService.save(track);
        return "redirect:/tracks/" + savedTrack.getId();
    }

    @PostMapping("/{trackId}/delete")
    public String processDelete(@PathVariable Long trackId) {
        trackService.delete(trackId);
        return "redirect:/";
    }
}
