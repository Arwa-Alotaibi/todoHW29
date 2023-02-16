package com.example.todohw29;
import com.example.todohw29.Model.Todo;
import com.example.todohw29.Model.User;
import com.example.todohw29.Repository.UserRepository;
import com.example.todohw29.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;
    User user1 ,user2, user3;

    @BeforeEach
    void setUp() {
        user1 = new User(null, "arwa", "12345", "ADMIN", null);

    }
    @Test
    public void RegisterUserTest(){
        userService.Register(user1);
        verify(userRepository,times(1)).save(user1);

    }
    }
