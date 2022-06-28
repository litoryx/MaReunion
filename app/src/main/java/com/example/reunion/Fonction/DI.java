package com.example.reunion.Fonction;

import com.example.reunion.service.DummyMeetingApiService;
import com.example.reunion.service.MeetingApiService;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService(){return service;}

    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
