package com.hackerrank.gevents.controller;

import com.hackerrank.gevents.model.Event;
import com.hackerrank.gevents.repository.EventRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event request){
        return new ResponseEntity<>(eventRepository.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(){
        return new ResponseEntity<>(eventRepository.findAll(Sort.by("id")),HttpStatus.OK);
    }

    @GetMapping("/repos/{repoId}/events")
    public ResponseEntity<List<Event>> getEventsByRepoId(@PathVariable Integer repoId){
       List<Event> list = eventRepository.findAllByRepoIdOrderByIdAsc(repoId);
       if(list.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
           return new ResponseEntity<>(list, HttpStatus.OK);
       }
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer eventId){
        Optional<Event> isExistingEvent = eventRepository.findById(eventId);
        return isExistingEvent.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
