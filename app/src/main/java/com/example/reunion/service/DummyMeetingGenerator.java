package com.example.reunion.service;





import com.example.reunion.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    String[] items = new String[] {"Réunion 1", "Réunion 2", "Réunion 3"};

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(new Meeting("Jean","Réunion 14","fuifiz@gmail.com,fpsjfdp@hotmail.bout","09/11/20","Materiel"),
            new Meeting("Hemsworth","Réunion 12","fuifiz@gmail.com,fpsjfdp@hotmail.bout","10/05/20","Essence"),
            new Meeting("Johansson","Réunion 8","fuifiz@gmail.com,fpsjfdp@hotmail.bout","02/06/21","Rachat"));

    static List<Meeting> generateListMeeting(){ return new ArrayList<>(DUMMY_MEETINGS); }
}
