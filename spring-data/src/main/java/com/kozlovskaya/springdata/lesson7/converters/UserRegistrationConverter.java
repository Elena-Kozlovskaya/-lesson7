package com.kozlovskaya.springdata.lesson7.converters;

import com.kozlovskaya.springdata.lesson7.dto.UserRegistrationDto;
import com.kozlovskaya.springdata.lesson7.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationConverter {
    public User dtoToEntity(UserRegistrationDto userRegistrationDto) {
        return new User(userRegistrationDto.getId(), userRegistrationDto.getUsername(), userRegistrationDto.getPassword(), userRegistrationDto.getEmail());
    }

    public UserRegistrationDto entityToDto(User user) {
        return new UserRegistrationDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
    }
}
