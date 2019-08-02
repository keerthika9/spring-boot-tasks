package com.stackroute.muzixtrack.controller;

import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.exceptions.TrackNotFoundException;
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

  @PostMapping("/track")
  public ResponseEntity<?> save(@RequestBody Track track) {
    ResponseEntity responseEntity;
    try {
      Track savedTrack = trackService.save(track);
      responseEntity = new ResponseEntity<String>("succesfullycreated", HttpStatus.CREATED);
    } catch (TrackAlreadyExistsException ex) {

      responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
      ex.printStackTrace();
    }
    return responseEntity;
  }

  @GetMapping("track/{id}")
  public ResponseEntity<?> getTrackById(@PathVariable int id) {
    ResponseEntity responseEntity;
    System.out.println(id);
    try {
      Track retrivedTrack = trackService.getById(id);
      return new ResponseEntity<>(retrivedTrack, HttpStatus.CREATED);
    } catch (TrackNotFoundException exe) {
      responseEntity = new ResponseEntity(exe.getMessage(), HttpStatus.NOT_FOUND);
      exe.printStackTrace();
    }
    return responseEntity;
  }

  @GetMapping("tracks")
  //to get the List of track
  public ResponseEntity<?> getAllTracks() throws Exception {
    ResponseEntity responseEntity;
    try {
      responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);
    } catch (Exception e) {
      responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
      e.printStackTrace();
    }
    return responseEntity;
  }

  @DeleteMapping("track/{id}")
  public ResponseEntity<?> deleteById(@PathVariable int id) {
    ResponseEntity responseEntity;
    System.out.println(id);
    try {
      Track retrivedTrack = trackService.getById(id);
      return new ResponseEntity<>(retrivedTrack, HttpStatus.CREATED);
    } catch (TrackNotFoundException e) {
      responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
      e.printStackTrace();
    }
    return responseEntity;
  }

  @PutMapping("track/{id}")
  //to update the track
  public ResponseEntity<?> updateTrackById(@PathVariable int id) throws TrackNotFoundException {
    ResponseEntity responseEntity;
    try {
      Track trackUpdated = trackService.getById(id);
      responseEntity = new ResponseEntity<>(trackUpdated, HttpStatus.UPGRADE_REQUIRED);
    } catch (TrackNotFoundException ex) {
      responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
      ex.printStackTrace();
    }
    return responseEntity;
  }

  @GetMapping("tracks/{name}")
  //to get the track by name
  public ResponseEntity<?> getTrackByName(@PathVariable int id) {
    ResponseEntity responseEntity;
    System.out.println(id);
    try {
      Track retrivedTrack = trackService.getById(id);
      return new ResponseEntity<>(retrivedTrack, HttpStatus.CREATED);
    } catch (TrackNotFoundException exe) {
      responseEntity = new ResponseEntity(exe.getMessage(), HttpStatus.NOT_FOUND);
      exe.printStackTrace();
    }
    return responseEntity;
  }


}


