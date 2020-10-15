package com.santander.challenge.mapper;

import com.santander.challenge.model.MeetUp;
import com.santander.challenge.model.MeetUpUsers;
import com.santander.challenge.model.User;
import com.santander.challenge.model.response.MeetUpResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guillermovarelli
 */
public class MeetUpMapperTest {

    @Autowired
    MeetUpMapper meetUpMapper;

    MeetUp meetUp;

    @Before
    public void init(){
        meetUpMapper = new MeetUpMapper();
        meetUp = new MeetUp();
        meetUp.setCity("Fake Street");
        meetUp.setId(1L);
        meetUp.setName("Test Meet");
        List<MeetUpUsers> users= new ArrayList<>();
        users.add(new MeetUpUsers(new MeetUp(),
                new User("Dummy user","Test","Test","user"),
                Date.from(Instant.now()),
                true));
        meetUp.setMeetUpUser(users);
    }

    @Test
    public void success(){
        MeetUpResponse response = meetUpMapper.mapToDto(meetUp);

        Assert.assertEquals(response.getCity(),meetUp.getCity());
        Assert.assertEquals(response.getId(),meetUp.getId());
        Assert.assertEquals(response.getName(),meetUp.getName());

    }
}
