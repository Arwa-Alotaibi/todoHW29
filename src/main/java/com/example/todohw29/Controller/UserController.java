package com.example.todohw29.Controller;


import com.example.todohw29.Model.User;
import com.example.todohw29.Service.UserService;
import com.example.todohw29.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService ;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity Register(@Valid @RequestBody User user){
        userService.Register(user);
        return ResponseEntity.status(200).body(new ApiResponse("registered"));
    }
}
