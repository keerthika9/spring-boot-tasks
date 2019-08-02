package com.stackroute.muzixtrack.service;

import com.stackroute.muzixtrack.domain.Track;

import java.util.List;

public interface TrackService {
  public Track save(Track track);
  public Track getById(int id);
  public List<Track> getAllTracks();
  public Track deleteById(int id);
  public Track updateTrackbyId(int id, Track track);
  public List<Track>trackByName(String name);
}
