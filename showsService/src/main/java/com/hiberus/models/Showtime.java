package com.hiberus.models;

import javax.persistence.Embeddable;

@Embeddable
public class Showtime {

    private String room;

    private String time;

    // Getters y setters

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTimes(String time) {
        this.time = time;
    }
}