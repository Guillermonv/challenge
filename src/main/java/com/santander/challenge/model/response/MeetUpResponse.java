package com.santander.challenge.model.response;

import java.time.LocalDate;
import java.util.List;

/**
 * @author guillermovarelli
 */
public class MeetUpResponse {
    private Long id;
    private String name;
    private LocalDate meetUpDate;
    private String city;
    private List<String> enrrolled;

    public List<String> getEnrrolled() {
        return enrrolled;
    }

    public void setEnrrolled(List<String> enrrolled) {
        this.enrrolled = enrrolled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getMeetUpDate() {
        return meetUpDate;
    }

    public void setMeetUpDate(LocalDate meetUpDate) {
        this.meetUpDate = meetUpDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
