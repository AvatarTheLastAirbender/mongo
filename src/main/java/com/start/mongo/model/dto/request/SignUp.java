package com.start.mongo.model.dto.request;

import com.start.mongo.helper.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {
    private String email;
    private String username;
    private String password;
    private UserType userType;
}
