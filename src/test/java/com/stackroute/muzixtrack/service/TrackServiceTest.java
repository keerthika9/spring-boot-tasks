package com.stackroute.muzixtrack.service;
import com.stackroute.muzixtrack.domain.Track;
import com.stackroute.muzixtrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixtrack.exceptions.TrackNotFoundException;
import com.stackroute.muzixtrack.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;

public class TrackServiceTest {
 private Track track;
  //Create a mock for UserRepository
  @Mock
  TrackRepository trackRepository;
  //Inject the mocks as dependencies into UserServiceImpl
  @InjectMocks
  TrackServiceImpl trackService;
  List<Track> list = null;
  @Before
  public void setUp() throws Exception {
//Initialising the mock object
    MockitoAnnotations.initMocks(this);
    track=new Track();
    track.setId(9);
    track.setName("ekbaar");
    track.setComments("nice");
    list = new ArrayList<>();
    list.add(track);
  }
  @After
  public void tearDown() throws Exception {
   track=null;
  }
  @Test
  public void saveTrackSuccess() throws TrackAlreadyExistsException {
    when(trackRepository.existsById(track.getId())).thenReturn(false);
    when(trackRepository.save((Track)any())).thenReturn(track);
    Track saveTrack = trackService.save(track);
    Assert.assertEquals(track, saveTrack);
    //verify here verifies that trackRepository save method is only called ones
    verify(trackRepository, Mockito.times(1)).save(track);
  }
 @Test(expected = TrackAlreadyExistsException.class)
  public void givenTrackAsInputShouldReturnSaveTrackTestFailure() throws TrackAlreadyExistsException {
    when(trackRepository.save(any())).thenReturn(null);
    Track savedTrack = trackService.save(track);
    System.out.println("savedTrack" + savedTrack);
   verify(trackRepository, Mockito.times(0)).save(track);

  }
  @Test
  public void givenDataAsInputShouldReturnAllTracks() throws Exception {
    trackRepository.save(track);
    //stubbing the mock to return specific data
    when(trackRepository.findAll()).thenReturn(list);
    List<Track> trackList = trackService.getAllTracks();
    Assert.assertEquals(list, trackList);
   verify(trackRepository, Mockito.times(1)).getAllTracks(track);
  }
  @Test
  public void givenIdAsInputShouldReturnTrack() throws TrackNotFoundException {
    trackRepository.save(track);
    when(trackRepository.existsById(9)).thenReturn(true);
    when(trackRepository.findById(9)).thenReturn(track);
    Track getTrack=trackService.getById(9);
    Assert.assertEquals(track,getTrack);
  }
  @Test
  public void givenNameAsInputShouldReturnTrack()throws Exception{
    trackRepository.save(track);
    when(trackRepository.findByName(any())).thenReturn(list);
    List<Track> getTrack=trackService.findByName("ekbaar");
    Assert.assertEquals(list,getTrack);
   verify(trackRepository, Mockito.times(1)).findByName(track);
  }
  @Test
  public void givenIdAsInputShouldDeleteTrack()throws TrackNotFoundException{
    trackRepository.save(track);
    when(trackRepository.existsById(9)).thenReturn(true);
    when(trackRepository.findById(9)).thenReturn(track);
    Track deleteTrack=trackService.deleteById(9);
    Assert.assertEquals(track,deleteTrack);
   verify(trackRepository, Mockito.times(1)).deleteById(track);
  }
  @Test
  public void givenIdAsInputShouldUpdateTrack()throws TrackNotFoundException{
    trackRepository.save(track);
    when(trackRepository.existsById(9)).thenReturn(true);
    when(trackRepository.findById(9)).thenReturn(track);
    Track updateTrack=trackService.updateTrackById(9,track);
    Assert.assertEquals(track,updateTrack);
   verify(trackRepository, Mockito.times(1)).updateTrackById(track);
  }

  @Test(expected = TrackAlreadyExistsException.class)
  public void saveUserTestFailure() throws TrackAlreadyExistsException {
    when(trackRepository.save(any())).thenReturn(null);
    Track savedTrack = trackService.save(track);
    System.out.println("savedTrack" + savedTrack);
    //Assert.assertEquals(user,savedUser);
      /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
      userService.saveUser(user);*/
  }
  @Test(expected = TrackNotFoundException.class)
    public void givenNegativeIdAsInputShouldReturnTrackNotFound() throws TrackNotFoundException {
    trackRepository.save(track);
    //stubbing the mock to return specific data
    when(trackRepository.findById(9)).thenReturn(track);
    Track gettrack = trackService.getById(9);
    Assert.assertEquals(track, gettrack);
   verify(trackRepository, Mockito.times(1)).findById(track);
  }
}


