package com.stackroute.muzixtrack.repository;

import com.stackroute.muzixtrack.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

//When a class is annotated with @RunWith or extends a class annotated with @RunWith, JUnit will invoke the class it references to runthe tests in that class instead of the runner built into JUnit
@RunWith(SpringRunner.class)//The SpringRunner provides support for loading a Spring ApplicationContext and having beans
//@DataJpaTest
@SpringBootTest
public class TrackRepositoryTest {
  @Autowired//@Autowired annotation is used for automatic dependency injection
   private TrackRepository trackRepository;
  private Track track;

  @Before
  public void setup() {
    track = new Track();
    track.setId(1);
    track.setName("keer");
    track.setComments("super");
  }

  @After
  public void teardown() {
    track=null;
  }

@Test
  public void givenDetailsAsInputShouldReturnCorrectId() {
    trackRepository.save(track);
    Track trackDetails = trackRepository.findById(track.getId());
    assertEquals(1, trackDetails.getId());
  }
  @Test
  public void givenNameAsInputShouldReturnTrackDetails() {
    Track track = new Track(2, "akhi", "great");
    trackRepository.save(track);
    List<Track> list = trackRepository.findAll();
    Assert.assertEquals("akhi", list.get(0).getName());
  }
@Test
  public void givenIdAsInputShouldReturnTrackFailure(){
  trackRepository.save(track);
  Track trackDetails = trackRepository.findById(track.getId());
  Assert.assertNotSame(3,trackDetails.getId());
  }
  @Test
  public void givenDetailsAsInputShouldReturnSavedTrack() {
    Track trackInput = new Track(3, "lakalaka", "horror");
    trackRepository.save(trackInput);
    List<Track> trackDetails = trackRepository.findAll();
    List<Track> expectedDtails = new ArrayList<>();
    expectedDtails.add(trackInput);
    assertEquals(expectedDtails, trackDetails);
  }

  @Test
  public void givenTrackShouldDeleteTrackById(){
    trackRepository.save(track);
    trackRepository.deleteById(track.getId());
    assertEquals(true, trackRepository.existsById(track.getId()));

  }
  @Test
  public void gievnTrackShouldUpdateTrackById() {
    Track track1 = new Track(1, "SAHO", "title track");
    Track track2 = new Track(2, "LAKA", "intro song");
    trackRepository.save(track1);
    trackRepository.save(track2);


    Track trackList = trackRepository.findById(track1.getId());
    trackList.setName(track2.getName());

    assertEquals(trackList.getName(), track2.getName());

  }
}
