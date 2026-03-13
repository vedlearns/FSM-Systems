package com.music.streaming.platform.controller;

import com.music.streaming.platform.common.exception.ResourceNotFoundException;
import com.music.streaming.platform.dto.ArtistRequest;
import com.music.streaming.platform.model.Artist;
import com.music.streaming.platform.service.ArtistService;
import com.music.streaming.platform.service.impl.ArtistServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/platform/v1/artists")
@AllArgsConstructor
public class ArtistController {
    private final ArtistService artistService;
    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody ArtistRequest request){
        return new ResponseEntity<>(artistService.createArtist(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists(){
        return new ResponseEntity<>(artistService.getAllArtists(), HttpStatus.OK);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId){
        if(artistId<=0)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try{
            return new ResponseEntity<>(artistService.getArtistById(artistId),HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{artistId}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long artistId, @RequestBody ArtistRequest request){
        if(artistId<=0)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try{
            return new ResponseEntity<>(artistService.updateArtist(artistId, request), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteAArtist(@PathVariable Long artistId){
        if(artistId<=0)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Artist artistById = artistService.getArtistById(artistId);
            artistService.deleteArtist(artistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
