package com.music.playlist.service.impl;

import com.music.playlist.dto.PlayListRequest;
import com.music.playlist.model.PlayList;
import com.music.playlist.repository.PlayListRepository;
import com.music.playlist.service.PlayListService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlayListServiceImpl implements PlayListService {

    private PlayListRepository playListRepository;

    public PlayListServiceImpl(PlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
    }

    @Override
    public PlayList createPlayList(PlayListRequest playListRequest) {
        PlayList  playList = new PlayList();
        playList.setName(playListRequest.name());
        playList.setTracksCount(playListRequest.tracksCount());
        playList.setCreatedDate(new Date());
        return playListRepository.save(playList);
    }

    @Override
    public List<PlayList> getPlayLists() {
        return playListRepository.findAll();
    }

    @Override
    public PlayList getPlayListByID(Long id) {
        Optional<PlayList> byId = playListRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public void deletePlayList(Long id) {
        Optional<PlayList> byId = playListRepository.findById(id);
        if(byId.isPresent()){
            playListRepository.deleteById(id);
        }
    }
}
