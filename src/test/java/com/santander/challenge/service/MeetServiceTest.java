package com.santander.challenge.service;

import com.santander.challenge.error.MeetException;
import com.santander.challenge.mapper.MeetUpMapper;
import com.santander.challenge.model.MeetUp;
import com.santander.challenge.model.MeetUpUsers;
import com.santander.challenge.model.User;
import com.santander.challenge.model.response.MeetUpResponse;
import com.santander.challenge.repository.MeetUpRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * @author guillermovarelli
 */
@RunWith(MockitoJUnitRunner.class)
public class MeetServiceTest {

    @Mock
    private MeetUpRepository meetUpRepository;

    @Mock
    private MeetUpMapper meetUpMapper;
    @InjectMocks
    private MeetService service;

    List<MeetUp> mockedList = new ArrayList<>();

    @Before
    public void init() {

        MeetUp meet = new MeetUp();
        meet.setCity("Fake Street 123");
        meet.setId(1L);
        meet.setMeetUpDate(LocalDate.now());
        meet.setName("Test MeetUp");
        List<MeetUpUsers> meetUpUsersList = new ArrayList<>();
        MeetUpUsers meetUpUser = new MeetUpUsers();
        meetUpUser.setUser(new User("Test","test","test","user"));
        meetUpUsersList.add(meetUpUser);
        meet.setMeetUpUser(meetUpUsersList);

        mockedList.add(meet);


        List<MeetUpResponse> meetUpResponses= new ArrayList<>();
        MeetUpResponse meetUpResponse = new MeetUpResponse();
        List<String> enrolledUsers = new ArrayList<>();
        enrolledUsers.add("Dummy 1");
        meetUpResponse.setEnrrolled(enrolledUsers);
        meetUpResponse.setName("Test MeetUp");
        meetUpResponse.setCity("Fake Street 123");
        meetUpResponse.setMeetUpDate(LocalDate.now());
        meetUpResponses.add(meetUpResponse);

        when(meetUpMapper.mapToDto(anyList())).thenReturn(meetUpResponses);

        when(meetUpRepository.findAll()).thenReturn(mockedList);

    }

    @Test
    public void whenFindAllMeetUpsThenReturnList(){
        List<MeetUpResponse> result =service.findAllMeetUp();

        Assert.assertEquals(result.get(0).getName(),mockedList.get(0).getName());
        Assert.assertEquals(result.get(0).getMeetUpDate(),mockedList.get(0).getMeetUpDate());
        Assert.assertEquals(result.get(0).getCity(),mockedList.get(0).getCity());
        Assert.assertEquals(result.get(0).getEnrrolled().size(),mockedList.get(0).getMeetUpUser().size());


    }

    @Test(expected = MeetException.class)
    public void whenDeleteInvalidMeetUpThrowsError() throws MeetException {
        service.removeMeetUp(3L);
    }
    @Test
    public void whenFindAllThenReturnValidList() throws Exception {
        service.createMeetUp("Test","test",LocalDate.now());

    }
}
