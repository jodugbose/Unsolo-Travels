package com.interswitch.Unsolorockets.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "travellers")
public class Traveller extends User {
}
