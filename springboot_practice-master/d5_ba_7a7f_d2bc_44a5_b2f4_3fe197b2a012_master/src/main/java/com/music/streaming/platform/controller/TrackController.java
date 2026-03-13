package com.music.streaming.platform.controller;

import com.music.streaming.platform.common.exception.ResourceNotFoundException;
import com.music.streaming.platform.dto.TrackRequest;
import com.music.streaming.platform.model.Track;
import com.music.streaming.platform.service.TrackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/platform/v1/tracks")
@AllArgsConstructor
public class TrackController {

    private final TrackService trackService;
    @PostMapping
    public ResponseEntity<Track> createTrack(@RequestBody TrackRequest request){
        return new ResponseEntity<>(trackService.createTrack(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Track>> getAllTracks(){
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long trackId){
       try{
           return new ResponseEntity<>(trackService.getTrackById(trackId), HttpStatus.OK);
       }catch (ResourceNotFoundException e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping("/{trackId}")
    public ResponseEntity<Track> updateTrack(@PathVariable Long trackId, @RequestBody TrackRequest request){
        try{
            return new ResponseEntity<>(trackService.updateTrack(trackId, request), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<Void> deleteTrackById(@PathVariable Long trackId){
        trackService.deleteTrack(trackId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
