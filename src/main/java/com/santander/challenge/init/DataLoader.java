package com.santander.challenge.init;

import com.santander.challenge.model.MeetUp;
import com.santander.challenge.model.MeetUpUsers;
import com.santander.challenge.model.User;
import com.santander.challenge.repository.MeetUpRepository;
import com.santander.challenge.repository.MeetUpUsersRepository;
import com.santander.challenge.repository.UserRepository;
import com.santander.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;


/**
 * @author guillermovarelli
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeetUpRepository meetUpRepository;

    @Autowired
    private MeetUpUsersRepository meetUpUsersRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        User user = new User("Guillermo", "Varelli", "admin", "admin");
        user.setPassword(service.encode("qwerty123"));
        userRepository.save(user);

        User user1 = new User("Juan Carlos", "Batman", "staff", "admin");
        user1.setPassword(service.encode("qwerty123"));
        userRepository.save(user1);

        User dummyUser1 = new User("Dummy", "user1", "user1", "user");
        dummyUser1.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser1);

        User dummyUser2 = new User("Dummy", "user2", "user2", "user");
        dummyUser2.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser2);

        User dummyUser3 = new User("Dummy", "user3", "user3", "user");
        dummyUser3.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser3);

        User dummyUser4 = new User("Dummy", "user4", "user4", "user");
        dummyUser4.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser4);

        User dummyUser5 = new User("Dummy", "user5", "user5", "user");
        dummyUser5.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser5);

        User dummyUser6 = new User("Dummy", "user6", "user6", "user");
        dummyUser6.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser6);

        User dummyUser7 = new User("Dummy", "user7", "user7", "user");
        dummyUser7.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser7);

        User dummyUser8 = new User("Dummy", "user8", "user8", "user");
        dummyUser8.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser8);

        User dummyUser9 = new User("Dummy", "user9", "Dummy.user9", "user");
        dummyUser9.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser9);

        User dummyUser10 = new User("Dummy", "user10", "Dummy.user10", "user");
        dummyUser10.setPassword(service.encode("qwerty123"));
        userRepository.save(dummyUser10);

        MeetUp meetUp = new MeetUp("Java Programming", LocalDate.of(2020, Month.FEBRUARY, 26), "Cordoba");
        meetUpRepository.save(meetUp);
        MeetUp meetUp1 = new MeetUp("Cloud MeetUp", LocalDate.of(2020, Month.MARCH, 29), "Salta");
        meetUpRepository.save(meetUp1);

        MeetUp meetUp2 = new MeetUp("Python ML meetUp", LocalDate.of(2020, Month.APRIL, 17), "Buenos Aires");
        meetUpRepository.save(meetUp2);

        MeetUpUsers meetUpUsers = new MeetUpUsers(meetUp, user, new java.util.Date(), false);
        meetUpUsersRepository.save(meetUpUsers);

        MeetUpUsers meetUpUsers1 = new MeetUpUsers(meetUp, user1, new java.util.Date(), false);
        meetUpUsersRepository.save(meetUpUsers1);

    }

}





