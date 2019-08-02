package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class TrackServiceImpl implements TrackService {
  private TrackRepository trackRepository;

  @Autowired
  public TrackServiceImpl(TrackRepository trackRepository) {

    this.trackRepository = trackRepository;
  }

  @Override
  public Track save(Track track) {
    Track savedTrack = trackRepository.save(track);
    return savedTrack;
  }

  @Override
  public Track getById(int id) {
    Track retriveTrack = trackRepository.findById(id).get();
    return retriveTrack;
  }

  @Override
  public List<Track> getAllTracks() {
    return trackRepository.findAll();
  }

  @Override
  public Track deleteById(int id) {
    Optional<Track> optionalTrack = trackRepository.findById(id);
    trackRepository.deleteById(id);
    return optionalTrack.get();
  }

  @Override
  public Track updateTrackbyId(int id, Track track) {
    Optional<Track> optionalTrack = trackRepository.findById(id);
    Track updateTrack = trackRepository.save(track);
    return updateTrack;
  }

  @Override
  public List<Track> trackByName(String name) {
    return trackRepository.trackByName(name);
  }

}
