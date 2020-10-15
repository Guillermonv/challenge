package com.santander.challenge.service;

import com.santander.challenge.error.ErrorEnum;
import com.santander.challenge.error.MeetException;
import com.santander.challenge.model.User;
import com.santander.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author guillermovarelli
 */

@Service
public class UserService {

    private RestTemplate rest;

    @Autowired
    private UserRepository userRepository;

    private static final String salt = "$2a$10$llw0G6IyibUob8h5XRt9xuRczaGdCm/AiV6SSjf5v78XS824EGbh";

    public User getUserDetail(Long userId) throws MeetException {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new MeetException(ErrorEnum.USER_NOT_EXIST.getDescription());
        }
        return user;
    }


    public User getUser(String username, String password) throws Exception {

        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new MeetException(ErrorEnum.USER_NOT_EXIST.getDescription());
        }

        String passwordEncoded = encode(password);
        if (!user.getPassword().equals(passwordEncoded)) {
            throw new MeetException(" Contraseña incorrecta" +user.getPassword() +  passwordEncoded);
        }
        return user;
    }

    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString(), salt);
    }

}
