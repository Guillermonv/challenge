package com.santander.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author guillermovarelli
 */

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@OneToMany (fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private Set<MeetUpUsers> meetUpUser;

	private String firstName;

	private String userName;

	private String lastName;
	@Column
	@ElementCollection(targetClass=String.class)
	private Set<String> assistedTo;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String role;


	public User(String firstName,String lastName, String userName,  String role) {
		this.firstName = firstName;
		this.userName = userName;
		this.lastName = lastName;
		this.role = role;
	}


	public User() {
		super();
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<MeetUpUsers> getMeetUpUser() {
		return meetUpUser;
	}

	public void setMeetUpUser(Set<MeetUpUsers> meetUpUser) {
		this.meetUpUser = meetUpUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<String> getAssistedTo() {
		return assistedTo;
	}

	public void setAssistedTo(Set<String> assistedTo) {
		this.assistedTo = assistedTo;
	}
}
