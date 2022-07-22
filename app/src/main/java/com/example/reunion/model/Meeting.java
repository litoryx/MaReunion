package com.example.reunion.model;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Meeting {

    private String name;
    private String room;
    private String emails;
    private LocalDate time;
    private String subject;

    public Meeting(String name, String room, String emails, LocalDate time, String subject) {
        this.name = name;
        this.room = room;
        this.emails = emails;
        this.time = time;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }


    public static Comparator<Meeting> ComparatorDate = new Comparator<Meeting>() {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.getTime().compareTo(o2.getTime());
        }
    };

    public static Comparator<Meeting> ComparatorPlace = new Comparator<Meeting>() {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.getRoom().compareTo(o2.getRoom());
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(name, meeting.name) && Objects.equals(room, meeting.room) && Objects.equals(emails, meeting.emails) && Objects.equals(time, meeting.time) && Objects.equals(subject, meeting.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, emails, time, subject);
    }
}
