package com.allegiancemd.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patient")
public class PatientEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   @Column(name = "firstName")
   private String firstName;
   @Column(name = "lastName")
   private String lastName;
}
