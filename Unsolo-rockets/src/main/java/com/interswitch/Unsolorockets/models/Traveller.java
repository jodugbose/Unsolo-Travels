package com.interswitch.Unsolorockets.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Setter
@Getter
@Entity
@Table(name = "travellers")
public class Traveller extends User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    private ArrayList<Trip> trips = new ArrayList<>();


}
