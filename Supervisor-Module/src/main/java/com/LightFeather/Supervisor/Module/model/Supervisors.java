package com.LightFeather.Supervisor.Module.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Supervisors {
    @Id
    @GeneratedValue(strategy = AUTO)
    private String firstName;
    private String lastName;
    private String jurisdiction;

    public Supervisors(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Supervisors supervisors = mapper.readValue(json, Supervisors.class);
            this.firstName = supervisors.firstName();
            this.lastName = supervisors.lastName();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String lastName() {
        return lastName;
    }

    private String firstName() {
        return firstName;
    }

    // Accessor method for firstName property
    public String getFirstName() {
        return firstName;
    }

    // Mutator method for firstName property
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Accessor method for lastName property
    public String getLastName() {
        return lastName;
    }

    // Mutator method for lastName property
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Accessor method for jurisdiction property
    public String getJurisdiction() {
        return jurisdiction;
    }

    // Mutator method for jurisdiction property
    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getName(){
        return String.format("%s %s %s",this.jurisdiction, this.lastName, this.firstName);
    }

    public void setName(String jurisdiction ){
        this.jurisdiction = jurisdiction;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
