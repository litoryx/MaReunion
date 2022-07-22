package com.example.reunion;

import com.example.reunion.Fonction.DI;
import com.example.reunion.model.Meeting;
import com.example.reunion.service.DummyMeetingGenerator;
import com.example.reunion.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class MeetingUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeeting();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void addMeetingsWithSuccess() {
        Meeting mUse = new Meeting(
                "Tristan",
                "Réunion 1",
                "dsoivfie",
                LocalDate.of(1990, 02, 06),
                "boubu");
        List<Meeting> expectedMeeting = new ArrayList<>(service.getMeeting());
        expectedMeeting.add(mUse);
        service.createMeeting(mUse);
        List<Meeting> mListMeetings = service.getMeeting();
        assertThat(mListMeetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeeting().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void verificationFilterDate() {
        Meeting meeting = new Meeting(
                "Mika",
                "Réunion 6",
                "dsoivfie",
                LocalDate.of(1990, 02, 06),
                "boubu");
        service.createMeeting(meeting);
        List<Meeting> ListMeetingbyDate = service.getMeetingbyDate(LocalDate.of(1990, 02, 06));
        assertThat(ListMeetingbyDate, IsIterableContainingInAnyOrder.containsInAnyOrder(meeting));
    }

    @Test
    public void verificationFilterRoom() {
        Meeting meeting = new Meeting(
                "Mika",
                "Réunion 96",
                "dsoivfie",
                LocalDate.of(1990, 02, 06),
                "boubu");
        service.createMeeting(meeting);
        String expRoom = "Réunion 96";
        List<Meeting> listMeetingbyRoom = service.getMeetingbyRoom(expRoom);
        List<Meeting> expectedMeeting = Arrays.asList(meeting);
        assertThat(listMeetingbyRoom, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }
}