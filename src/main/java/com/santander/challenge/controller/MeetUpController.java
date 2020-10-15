package com.santander.challenge.controller;

import com.santander.challenge.model.User;
import com.santander.challenge.model.response.BeerResponse;
import com.santander.challenge.model.response.MeetUpResponse;
import com.santander.challenge.model.response.WeatherResponse;
import com.santander.challenge.error.MeetException;
import com.santander.challenge.model.MeetUp;
import com.santander.challenge.service.MeetService;
import com.santander.challenge.service.UserService;
import com.santander.challenge.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author guillermovarelli
 */

@RestController
@RequestMapping("/")
public class MeetUpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetUpController.class);

    private static final String ERROR_MESSAGE_INTERNAL = "Error interno";

    @Autowired
    WeatherService weatherServicet;

    @Autowired
    private MeetService meetservice;
    @Autowired
    private UserService userService;

    @GetMapping("getWeather/{meetUpId}")
    @ApiOperation(value = "Obtener el clima")
    public ResponseEntity<?> getWeather(@PathVariable("meetUpId") Long meetUpId) throws Exception {
        try {
            WeatherResponse meetUpObtainTemperatureResponse = weatherServicet.getWeather(meetUpId);
            return new ResponseEntity<>(meetUpObtainTemperatureResponse, HttpStatus.OK);
        } catch (MeetException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @GetMapping("getUser/{userId}")
    @ApiOperation(value = "Obtener informacion del Usuario")
    public ResponseEntity<User> getUserbyId(@PathVariable("userId") Long userId) throws Exception {
        User result = userService.getUserDetail(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("createMeetUp")
    @ApiOperation(value = "Crear la meetUp")
    public void createMeetUp(@RequestBody MeetUp request) throws Exception {
        meetservice.createMeetUp(request.getName(), request.getCity(), request.getMeetUpDate());

    }

    @DeleteMapping("removeMeetUp/{id}")
    @ApiOperation(value = "remover Meet up")
    public ResponseEntity<String> removeMeetUp(@PathVariable("id") Long id) throws Exception {
        try {
            meetservice.removeMeetUp(id);
            return new ResponseEntity<>("La meetUp id : " + id + " fue removida", HttpStatus.OK);
        } catch (MeetException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

        }
    }

    @PostMapping("invite/{meetUpId}/user/{userId}")
    @ApiOperation(value = "invitar a un usuario a la meetUp")
    public ResponseEntity<String> inviteUSer(@PathVariable("meetUpId") Long meetUpId, @PathVariable("userId") Long userId) {
        try {
            meetservice.addUserToMeetUp(meetUpId, userId);
            return new ResponseEntity<>("User id : " + userId + " agregado en la " + meetUpId, HttpStatus.OK);
        } catch (MeetException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);
    
        }
    }
    @PostMapping("checkin/{meetUpId}/user/{userId}")
    @ApiOperation(value = "acepta la invitacion de una meetUp para un usuario")
    public ResponseEntity<String> checkinUser(@PathVariable("meetUpId") Long meetUpId, @PathVariable("userId") Long userId) {
        try {
            meetservice.checkinUserToMeetUp(meetUpId, userId);
            return new ResponseEntity<>("User id : " + userId + " agregado en la " + meetUpId, HttpStatus.OK);
        } catch (MeetException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

        }
    }

    @GetMapping("notification")
    @ApiOperation(value = "Muestra todas las meet ups disponibles")
    public ResponseEntity<?> findAllMeetUp() {
        try {
            List<MeetUpResponse> response = meetservice.findAllMeetUp();
            LOGGER.info("retorando todos las meets disponibles");
            response.stream().forEach(x -> System.out.println(x.toString()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(ERROR_MESSAGE_INTERNAL, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

        }
    }

    @GetMapping("beerQty/{meetUpId}")
    @ApiOperation(value = "encargado de devolver la cantidad de birras para una meetUp")
    public  ResponseEntity<?> beerQty(@PathVariable("meetUpId") long meetUpId) throws Exception {
        try{
            BeerResponse response=  meetservice.calculateBeerForMeetUp(meetUpId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (MeetException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }

}
}


