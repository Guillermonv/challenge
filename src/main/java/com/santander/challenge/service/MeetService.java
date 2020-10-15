package com.santander.challenge.service;

import com.santander.challenge.model.response.BeerResponse;
import com.santander.challenge.model.response.MeetUpResponse;
import com.santander.challenge.error.ErrorEnum;
import com.santander.challenge.error.MeetException;
import com.santander.challenge.mapper.MeetUpMapper;
import com.santander.challenge.model.MeetUp;
import com.santander.challenge.model.MeetUpUsers;
import com.santander.challenge.model.User;
import com.santander.challenge.model.response.WeatherResponse;
import com.santander.challenge.repository.MeetUpRepository;
import com.santander.challenge.repository.MeetUpUsersRepository;
import com.santander.challenge.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author guillermovarelli
 */

@Service
public class MeetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetService.class);

    @Autowired
    private MeetUpRepository meetUpRepository;

    @Autowired
    private MeetUpUsersRepository meetUpUsersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeetUpMapper mapper;

    @Autowired
    private WeatherService weatherService;

    public BeerResponse calculateBeerForMeetUp(Long meetupId) throws Exception {
        WeatherResponse weatherResponse = weatherService.getWeather(meetupId);
        Optional<MeetUp> meetUp = meetUpRepository.findById(meetupId);
        if (!meetUp.isPresent()) {
            LOGGER.error("meetUp  id : " + meetupId + " no existe");
            throw new MeetException(ErrorEnum.MEETUP_NOT_EXIST.getDescription());

        }
        MeetUpResponse meetUpResponse = this.mapper.mapToDto(meetUp.get());
        Integer qtyEnrolled = meetUpResponse.getEnrrolled().size();
        BeerResponse response = new BeerResponse();
        if (weatherResponse.getTemperature_max() >= 24)
            response.setBeerQty(qtyEnrolled * 2);
        else
            response.setBeerQty(qtyEnrolled);

        response.setSixPackQty(calculateSixPack(response.getBeerQty()));
        return response;
    }

    public List<MeetUpResponse> findAllMeetUp() {
        return this.mapper.mapToDto(meetUpRepository.findAll());
    }

    public void removeMeetUp(Long id) throws MeetException {
        if (!meetUpRepository.findById(id).isPresent()) {
            LOGGER.error("meetUp  id : " + id + " no existe");
            throw new MeetException(ErrorEnum.MEETUP_NOT_EXIST.getDescription());

        }
        this.meetUpRepository.deleteById(id);
    }

    public void createMeetUp(String name, String direction, LocalDate dateMeetUp) throws Exception {
        LOGGER.debug("Arranco con el proceso de crear la meetUp");


        MeetUp meetUp = new MeetUp(name, dateMeetUp, direction);
        meetUpRepository.save(meetUp);

        LOGGER.debug("Finalizo con el proceso de crear la meetUp");
    }


    public void addUserToMeetUp(Long meetUpId, Long userId) throws Exception {
        LOGGER.debug("Agregando usuario " + userId + " a la meetUp " + meetUpId);

        if (!meetUpRepository.findById(meetUpId).isPresent()) {
            LOGGER.error("meetUp  id : " + meetUpId + " no existe");
            throw new MeetException(ErrorEnum.MEETUP_NOT_EXIST.getDescription());
        }

        MeetUp meetUp = meetUpRepository.findById(meetUpId).get();
        if (!userRepository.findById(userId).isPresent()) {
            LOGGER.error("user : " + userId + " no existe");
            throw new MeetException(ErrorEnum.USER_NOT_EXIST.getDescription());
        }
        Optional<User> user = userRepository.findById(userId);
        if (!userRepository.findById(userId).isPresent()) {
            LOGGER.error("user : " + userId + " no existe");
            throw new MeetException(ErrorEnum.USER_NOT_EXIST.getDescription());
        }
        MeetUpUsers meetUpUsers = new MeetUpUsers(meetUp, user.get(), new Date(), false);
        meetUpUsersRepository.save(meetUpUsers);

    }


    public void checkinUserToMeetUp(Long meetUpId, Long userId) throws Exception {
        LOGGER.debug("Agregando usuario " + userId + " a la meetUp " + meetUpId);

        if (!meetUpRepository.findById(meetUpId).isPresent()) {
            LOGGER.error("meetUp  id : " + meetUpId + " no existe");
            throw new MeetException(ErrorEnum.MEETUP_NOT_EXIST.getDescription());
        }

        MeetUp meetUp = meetUpRepository.findById(meetUpId).get();
        if (!userRepository.findById(userId).isPresent()) {
            LOGGER.error("user : " + userId + " no existe");
            throw new MeetException(ErrorEnum.USER_NOT_EXIST.getDescription());
        }
        Optional<User> user = userRepository.findById(userId);
        if (!userRepository.findById(userId).isPresent()) {
            LOGGER.error("user : " + userId + " no existe");
            throw new MeetException(ErrorEnum.USER_NOT_EXIST.getDescription());
        }

        for(MeetUpUsers meetUpUser:user.get().getMeetUpUser()) {
            if(meetUpUser.getMeetUp().getId().equals(meetUpId)) {
                meetUpUser.setUserAttented(true);
                meetUpUsersRepository.save(meetUpUser);
            }
        }
        if(user.get().getAssistedTo()== null)
            user.get().setAssistedTo(new HashSet<>());

        user.get().getAssistedTo().add(meetUp.getName());
        userRepository.save(user.get());
    }


    private Integer calculateSixPack(Integer beers) {
        return BigDecimal.valueOf(beers).divide(BigDecimal.valueOf(6), 0, RoundingMode.CEILING).intValue();
    }



}

