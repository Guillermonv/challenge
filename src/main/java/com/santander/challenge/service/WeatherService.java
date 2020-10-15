package com.santander.challenge.service;

import com.santander.challenge.cache.WeatherCache;
import com.santander.challenge.client.WeatherClient;
import com.santander.challenge.error.ErrorEnum;
import com.santander.challenge.error.MeetException;
import com.santander.challenge.model.response.WeatherResponse;
import com.santander.challenge.mapper.WeatherMapper;
import com.santander.challenge.model.MeetUp;
import com.santander.challenge.repository.MeetUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author guillermovarelli
 */
@Service
public class WeatherService {
    @Autowired
    WeatherMapper weatherMapper;

    @Autowired
    WeatherClient weatherClient;

    @Autowired
    MeetUpRepository meetUpRepository;

    @Autowired
    WeatherCache weatherCache;

    public WeatherResponse getWeather(Long meetupId) throws Exception {

        MeetUp meetUp =meetUpRepository.findById(meetupId).orElseThrow(
                ()->new MeetException(ErrorEnum.MEETUP_NOT_EXIST.getDescription()));
        checkEffectiveDate(meetUp.getMeetUpDate());

        return weatherMapper.
                mapToWeatherResponse(
                        checkEffectiveDate(meetUp.getMeetUpDate())
                        ,weatherClient.getWeather(meetUp.getCity()));
    }

    private int checkEffectiveDate(LocalDate date) throws MeetException {
        if(date.isBefore(LocalDate.now()) || Period.between(LocalDate.now(),date).getDays()>7)
            throw new MeetException(ErrorEnum.INVALID_DATE.getDescription());
        return Period.between(LocalDate.now(),date).getDays();
    }
}
