package com.start.mongo.controller;

import com.start.mongo.config.utils.JwtTokenProvider;
import com.start.mongo.helper.GlobalCommonService;
import com.start.mongo.model.dto.request.Login;
import com.start.mongo.model.dto.request.UserDto;
import com.start.mongo.model.dto.response.JsonToken;
import com.start.mongo.repository.crud.UserRepository;
import com.start.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;

@SuppressWarnings("All")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final Timer timer = new Timer();
    @Autowired
    private GlobalCommonService globalCommonService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Login login) {
        if (login.getUsername().contains("@") && globalCommonService.validateEmail(login.getUsername()))
            return globalCommonService.getResponseEntityByMessageAndStatus("Invalid email", HttpStatus.BAD_REQUEST);

        Authentication authentication;
        final String token;
        ResponseEntity<?> tokenResponseEntity;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

            //    notification for login alert
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//
//                }
//            }, 0);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenProvider.generateToken(authentication);
            tokenResponseEntity = new ResponseEntity<>(new JsonToken(token), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            tokenResponseEntity = globalCommonService.getResponseEntityByMessageAndStatus("Invalid username or password.", HttpStatus.NOT_FOUND);
        }

        return tokenResponseEntity;
    }


    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) {
        String responseMessage;
        HttpStatus responseStatus = HttpStatus.NOT_ACCEPTABLE;

        boolean isExistUsername = userRepository.findOneByUsername(user.getUsername()).isPresent() || userRepository.findOneByEmail(user.getUsername()).isPresent();
        if (globalCommonService.validateEmail(user.getEmail())) {
            responseMessage = "Invalid email";
        } else if (isExistUsername) {
            responseMessage = !user.getUsername().contains("@") ? "Username already exists" : "Registered email id";
        } else {
            responseMessage = "New user created for " + user.getUsername();
            responseStatus = HttpStatus.CREATED;
            userService.save(user);
        }
        return globalCommonService.getResponseEntityByMessageAndStatus(responseMessage, responseStatus);
    }
}
