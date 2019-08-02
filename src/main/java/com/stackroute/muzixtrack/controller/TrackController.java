package com.stackroute.muzixtrack.controller;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrackController {
  private TrackService trackService;

  public TrackController() {
  }

  @Autowired
  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

  @PostMapping("track")
  public ResponseEntity<?> saveTrack(@RequestBody Track track) {
    Track savedTrack = trackService.save(track);
    return new ResponseEntity<>(savedTrack, HttpStatus.OK);
  }

  @GetMapping("track/{id}")
  public ResponseEntity<?> getTrackById(@PathVariable int id) {
    System.out.println(id);
    Track retrivedTrack = trackService.getById(id);
    return new ResponseEntity<>(retrivedTrack, HttpStatus.OK);
  }

  @GetMapping("tracks")
  public ResponseEntity<?> getAllUsers() {
    return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
  }

  @DeleteMapping("track/{id}")
  public ResponseEntity<?> deleteById(@PathVariable("id") int id) {

    Track trackRemoved = trackService.deleteById(id);
    return new ResponseEntity<>(trackRemoved, HttpStatus.OK);
  }

  @PatchMapping("track/{id}")
  public ResponseEntity<?> updateById(@PathVariable int id, @RequestBody Track track) {
    Track trackUpdated = trackService.updateTrackbyId(id, track);
    return new ResponseEntity<>(trackUpdated, HttpStatus.OK);

  }

}


