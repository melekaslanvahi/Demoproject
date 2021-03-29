package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
import com.example.demo.domain.entity.User;
//import org.springframework.com.example.demo.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

 */

@RestController
@RequestMapping("/api/users")
@Api(value="User Rest Service")
public class UserController {

    /*
    @RequestMapping("/")
    public String index() {
        return "Merhaba Spring Boot! Ben user com.example.demo.controller!";
    }

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<User> users(){
        return userRepository.findAll();
    }

    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    */

    /*
}

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public User signUp(@RequestBody SignupDTO signupDTO) {
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setType("ADMIN");
        //user.setPassword(bCryptPasswordEncoder.encode(signupDTO.getPassword()));
        return userRepository.save(user);
    }


     */
}