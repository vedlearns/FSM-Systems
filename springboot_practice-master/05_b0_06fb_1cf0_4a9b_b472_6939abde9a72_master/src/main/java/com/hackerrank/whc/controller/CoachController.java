package com.hackerrank.whc.controller;

import com.hackerrank.whc.model.Coach;
import com.hackerrank.whc.repository.CoachRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coach")
public class CoachController {

    final CoachRepository coachRepository;

    public CoachController(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @PostMapping
    public ResponseEntity<Coach> createCoach(@RequestBody Coach coach){
        return new ResponseEntity<>(coachRepository.save(coach), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoaches(){
        return new ResponseEntity<>(coachRepository.findAll(Sort.by("id")), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Integer id){
        Optional<Coach> isExistingCoach = coachRepository.findById(id);
        return isExistingCoach.map(coach -> new ResponseEntity<>(coach, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
