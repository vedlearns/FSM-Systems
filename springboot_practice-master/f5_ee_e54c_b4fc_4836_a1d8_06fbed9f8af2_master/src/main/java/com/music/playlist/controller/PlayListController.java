package com.music.playlist.controller;

import com.music.playlist.dto.PlayListRequest;
import com.music.playlist.model.PlayList;
import com.music.playlist.service.PlayListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/playlists")
public class PlayListController {

    private PlayListService playListService;

    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @PostMapping()
    public ResponseEntity<PlayList> createPlayList(@RequestBody PlayListRequest playListRequest){
        return new ResponseEntity<>(playListService.createPlayList(playListRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{playListId}")
    public ResponseEntity<PlayList> getPlayListById(@PathVariable Long playListId){
        if(playListId<=0||playListService.getPlayListByID(playListId)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(playListService.getPlayListByID(playListId), HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<PlayList>> getAllPlayLists(){
        return new ResponseEntity<>(playListService.getPlayLists(), HttpStatus.OK);
    }

    @DeleteMapping("/{playListId}")
    public ResponseEntity<Void> deletePlayList(@PathVariable Long playListId){
        if(playListId<=0||playListService.getPlayListByID(playListId)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            playListService.deletePlayList(playListId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
