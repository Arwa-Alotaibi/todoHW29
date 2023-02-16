package com.example.todohw29.Service;


import com.example.todohw29.Model.User;
import com.example.todohw29.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void Register(User user){
        user.setRole("USER");
        String hashedPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }


}
