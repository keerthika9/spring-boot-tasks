package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.exceptions.TrackNotFoundException;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
//@Profile("dev")
@Service

/*  Qualifier annotation is used to resolve the autowiring conflict,
    when there are multiple beans of same type "Primary" annotation will execute
*/
//@Qualifier
public class TrackDummyServiceImplementation implements TrackService {
  private TrackRepository trackRepository;

  @Autowired
  public TrackDummyServiceImplementation(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
  }

  @Override
  public Track save(Track track) throws TrackAlreadyExistsException {
    System.out.println("save track");
    return null;
  }

  @Override
  public Track getById(int id) throws TrackNotFoundException {
    return null;
  }

  @Override
  public List<Track> getAllTracks() {
    return null;
  }

  @Override
  public Track deleteById(int id) throws TrackNotFoundException {
    return null;
  }

  @Override
  public Track updateTrackById(int id, Track track) throws TrackNotFoundException {
    return null;
  }

  @Override
  public List<Track> findByName(String name) throws Exception {
    return null;
  }

}

