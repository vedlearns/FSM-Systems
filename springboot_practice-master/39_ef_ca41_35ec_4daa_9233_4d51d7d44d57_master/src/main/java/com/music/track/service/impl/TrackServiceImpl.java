package com.music.track.service.impl;

import com.music.track.dto.TrackRequest;
import com.music.track.model.Track;
import com.music.track.repository.TrackRepository;
import com.music.track.service.TrackService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track createTrack(TrackRequest trackRequest) {
        Track t = new Track();
        t.setAlbumName(trackRequest.albumName());
        t.setTitle(trackRequest.title());
        t.setReleaseDate(trackRequest.releaseDate());
        t.setPlayCount(trackRequest.playCount());
        return trackRepository.save(t);

    }
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public void deleteTrack(Long trackId) {
        if(trackRepository.findById(trackId).isPresent()){
            trackRepository.deleteById(trackId);
        }
    }

    @Override
    public List<Track> sortedTracks() {
        return trackRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    public Track getTrackById(Long id){
        if(id <=0)return null;
       Optional<Track> byId = trackRepository.findById(id);
       return byId.orElse(null);
    }
}
