package com.santander.challenge.mapper;

import com.santander.challenge.model.response.MeetUpResponse;
import com.santander.challenge.model.MeetUp;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guillermovarelli
 */
@Component
public class MeetUpMapper {
    public MeetUpResponse mapToDto(MeetUp meetup) {
        MeetUpResponse meetupDto = new MeetUpResponse();
        meetupDto.setId(meetup.getId());
        meetupDto.setName(meetup.getName());
        meetupDto.setCity(meetup.getCity());

        meetupDto.setEnrrolled(meetup.getMeetUpUser()
                .stream()
                .distinct()
                .map(x -> x.getUser().getFirstName()
                        .concat(" ")
                        .concat(x.getUser().getLastName())
                        .concat("| STATUS : ")
                        .concat(String.valueOf(x.isUserAttented()?"ACCEPTED":"PENDING")))
                .collect(Collectors.toList()));

        meetupDto.setMeetUpDate(meetup.getMeetUpDate());
        return meetupDto;
    }

    public List<MeetUpResponse> mapToDto(List<MeetUp> meetups) {
        return meetups.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}


