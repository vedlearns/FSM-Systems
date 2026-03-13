package com.music.streaming.platform.controller;

import com.music.streaming.platform.common.exception.ResourceNotFoundException;
import com.music.streaming.platform.dto.PlayListRequest;
import com.music.streaming.platform.model.PlayList;
import com.music.streaming.platform.service.PlayListService;
import com.music.streaming.platform.service.impl.PlayListServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music/platform/v1/playlists")
@AllArgsConstructor
public class PlayListController {
    private final PlayListService playListService;

    @PostMapping
    public ResponseEntity<PlayList> createPlaylist(@RequestBody PlayListRequest request){
        return new ResponseEntity<>(playListService.createPlayList(request), HttpStatus.OK);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<PlayList> getPlayListById(@PathVariable Long artistId){
        if(artistId<=0)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try{
            return new ResponseEntity<>(playListService.getPlayListById(artistId), HttpStatus.OK);
        }catch(ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deletePlayListById(@PathVariable Long artistId){
        playListService.deletePlayList(artistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
