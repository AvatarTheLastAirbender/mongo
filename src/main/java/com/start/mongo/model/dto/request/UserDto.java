package com.start.mongo.model.dto.request;

import com.start.mongo.helper.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String username;
    private String password;
    private UserType userType;
}
