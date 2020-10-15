package com.santander.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
/**
 * @author guillermovarelli
 */

@Entity
public class MeetUp implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private String name;
	private LocalDate meetUpDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meetUp",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MeetUpUsers> meetUpUser;

	private String city;



	public MeetUp() {
	}

	public MeetUp(String name, LocalDate meetUpDate, String city) {
		super();
		this.name = name;
		this.meetUpDate = meetUpDate;
		this.city = city;
	}


	@Override
	public String toString() {
		return "MeetUp{" +
				"id=" + id +
				", meetUpUser=" + meetUpUser +
				", meetUpDate=" + meetUpDate +
				", city='" + city + '\'' +
				'}';
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getMeetUpDate() {
		return meetUpDate;
	}

	public void setMeetUpDate(LocalDate meetUpDate) {
		this.meetUpDate = meetUpDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MeetUpUsers> getMeetUpUser() {
		return meetUpUser;
	}

	public void setMeetUpUser(List<MeetUpUsers> meetUpUser) {
		this.meetUpUser = meetUpUser;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
