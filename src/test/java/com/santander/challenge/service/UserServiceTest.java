package com.santander.challenge.service;

import com.santander.challenge.error.MeetException;
import com.santander.challenge.mapper.MeetUpMapper;
import com.santander.challenge.model.User;
import com.santander.challenge.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author guillermovarelli
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;

    User user = new User();

    @Before
    public void init() {

        user.setUserName("jon snow");
        user.setFirstName("Jon");
        user.setLastName("Test");
        user.setRole("User");
        user.setPassword(service.encode("123456"));
        when(userRepository.findByUserName("jon snow")).thenReturn(user);

    }

    @Test
    public void whenFindByValidUserNameThenReturnUser() throws Exception {
        User result = service.getUser("jon snow", "123456");
        Assert.assertEquals(result.getFirstName(), user.getFirstName());
        Assert.assertEquals(result.getLastName(), user.getLastName());
        Assert.assertEquals(result.getUserName(), user.getUserName());
        Assert.assertEquals(result.getRole(), user.getRole());

    }
    @Test(expected = MeetException.class)
    public void whenFindByUserWithInvalidPassThenThrowError() throws Exception {
        User result = service.getUser("jon snow", "wrong pass");
    }

    @Test(expected = MeetException.class)
    public void whenFindByUserWithInvalidUserThenThrowError() throws Exception {
        User result = service.getUser("Invalid user", "123456");
    }
}
