package com.example.todohw29;

import com.example.todohw29.Model.User;
import com.example.todohw29.Repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    UserRepository userRepository;

    User user1 ,user2, user3;


    @BeforeEach
    void setUp() {
        user1 = new User(null,"arwa","12345","ADMIN",null);

    }

    @Test
    public void findUserByUsernametest(){
        userRepository.save(user1);
        User user =userRepository.findUserByUsername(user1.getUsername());
        Assertions.assertThat(user).isEqualTo(user1.getUsername());
    }

    @Test
    public void  findUserByIdtest(){
        userRepository.save(user1);
        User user = userRepository.findUserById(user1.getId());
        Assertions.assertThat(user).isEqualTo(user1.getId());
    }
}
