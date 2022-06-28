package com.example.reunion.service;

import com.example.reunion.model.Meeting;

import java.time.LocalDate;
import java.util.List;

public interface MeetingApiService {


    List<Meeting> getMeeting();

    List<Meeting> getMeetingbyDate(LocalDate date);

    List<Meeting> getMeetingbyRoom(String room);

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);
}
