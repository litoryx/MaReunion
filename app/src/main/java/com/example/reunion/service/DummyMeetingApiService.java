package com.example.reunion.service;

import com.example.reunion.model.Meeting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateListMeeting();

    @Override
    public List<Meeting> getMeeting() {
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingbyDate(LocalDate date) {
        return null;
    }

    @Override
    public List<Meeting> getMeetingbyRoom(String room) {

        List<Meeting> result = new ArrayList<>();

        for (Meeting meeting: meetings) {
          if(meeting.getRoom().equals(room)){
              result.add(meeting);
          }
        }
        return result;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
