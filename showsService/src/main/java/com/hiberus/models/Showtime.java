package com.hiberus.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Showtime {

    private Integer id;
    private String room;
    private String time;
}