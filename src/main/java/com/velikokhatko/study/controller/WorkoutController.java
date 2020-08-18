package com.velikokhatko.study.controller;

import com.velikokhatko.study.service.WorkoutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/workours")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/{workoutId}")
    public ModelAndView getUserByUserId(@PathVariable String workoutId) {
        ModelAndView mav = new ModelAndView("workouts/workout");
        mav.addObject("workout", workoutService.getWorkoutDTOById(Long.valueOf(workoutId)));
        return mav;
    }
}
