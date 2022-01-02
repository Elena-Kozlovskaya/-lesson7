package com.kozlovskaya.springdata.lesson7.controllers;

import com.kozlovskaya.springdata.lesson7.converters.UserRegistrationConverter;
import com.kozlovskaya.springdata.lesson7.dto.UserRegistrationDto;
import com.kozlovskaya.springdata.lesson7.entities.User;
import com.kozlovskaya.springdata.lesson7.services.UserService;
import com.kozlovskaya.springdata.lesson7.validators.UserRegistrationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final UserRegistrationConverter userRegistrationConverter;
    private final UserRegistrationValidator userRegistrationValidator;

    @PostMapping("/registration")
    public void addNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        userRegistrationValidator.validate(userRegistrationDto);
        User user = userRegistrationConverter.dtoToEntity(userRegistrationDto);
        userService.createNewUser(user);
    }
}
