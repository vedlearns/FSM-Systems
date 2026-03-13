package com.artist.controller;

import com.artist.dto.ArtistRequest;
import com.artist.model.Artist;
import com.artist.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping()
    public ResponseEntity<Artist> createPlayList(@RequestBody ArtistRequest artistRequest){
        return new ResponseEntity<>(artistService.createArtist(artistRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId){
        if(artistId<=0 || artistService.getArtistByID(artistId) ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(artistService.getArtistByID(artistId), HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists(){
       return new ResponseEntity<>(artistService.getArtists(),HttpStatus.OK);
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long artistId){
        if(artistId<=0 || artistService.getArtistByID(artistId) ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            artistService.deleteArtist(artistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
