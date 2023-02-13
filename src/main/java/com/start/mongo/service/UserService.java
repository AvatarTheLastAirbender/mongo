package com.start.mongo.service;

import com.start.mongo.model.document.Roles;
import com.start.mongo.model.document.User;
import com.start.mongo.model.dto.request.UserDto;
import com.start.mongo.repository.crud.RoleRepository;
import com.start.mongo.repository.crud.UserRepository;
import com.start.mongo.repository.data.UserDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@SuppressWarnings({"All"})
@Service
public class UserService implements UserDtoRepository, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Lazy
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findOneByUsername(username);
        UserDetailsImpl userDetails;
        if (user.isPresent()) {
            userDetails = new UserDetailsImpl();
            userDetails.setUser(user.get());
            userDetails.setRoles(roleRepository.findByUserId(user.get().getId()).orElse(null));
        } else {
            throw new UsernameNotFoundException("User not exists with username: " + username);
        }
        return userDetails;
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        String id = userRepository.save(user).getId();

        Roles userRoles = new Roles();
        userRoles.setUserId(id);
        switch (userDto.getUserType()) {
            case ADMIN:
                userRoles.setRoles(List.of("ADMIN", "CUSTOMER", "SERVICE_PROVIDER"));
                break;
            case CUSTOMER:
                userRoles.setRoles(List.of("CUSTOMER"));
                break;
            case SERVICE_PROVIDER:
                userRoles.setRoles(List.of("SERVICE_PROVIDER"));
                break;
        }

        roleRepository.save(userRoles);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username).orElse(null);
    }
}

