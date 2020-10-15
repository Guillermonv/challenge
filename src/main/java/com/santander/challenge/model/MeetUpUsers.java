package com.santander.challenge.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author guillermovarelli
 */

@Entity
public class MeetUpUsers {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_up_id")
    private MeetUp meetUp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    private Date userMeetUpDate;

    private boolean userAttented;

    public MeetUpUsers() {
		super();
	}
    
		public MeetUpUsers(MeetUp meetUp, User user, Date userMeetUpDate, boolean userAttented) {
		super();
		this.meetUp = meetUp;
		this.user = user;
		this.userMeetUpDate = userMeetUpDate;
	}

	public Date getUserMeetUpDate() {
		return userMeetUpDate;
	}

	public void setUserMeetUpDate(Date userMeetUpDate) {
		this.userMeetUpDate = userMeetUpDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeetUp getMeetUp() {
		return meetUp;
	}

	public void setMeetUp(MeetUp meetUp) {
		this.meetUp = meetUp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUserAttented() {
		return userAttented;
	}

	public void setUserAttented(boolean userAttented) {
		this.userAttented = userAttented;
	}
}
