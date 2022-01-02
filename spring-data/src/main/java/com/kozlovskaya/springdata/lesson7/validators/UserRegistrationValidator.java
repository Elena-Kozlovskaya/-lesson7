package com.kozlovskaya.springdata.lesson7.validators;

import com.kozlovskaya.springdata.lesson7.dto.UserRegistrationDto;
import com.kozlovskaya.springdata.lesson7.exeptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRegistrationValidator {
    public void validate(UserRegistrationDto userRegistrationDto) {
        List<String> errors = new ArrayList<>();
        if (userRegistrationDto.getPassword().isBlank()) {
            errors.add("Пароль не может иметь пустое значение");
        }
        if (userRegistrationDto.getUsername().isBlank()) {
            errors.add("Пользователь не может иметь пустое имя");
        }
        if (userRegistrationDto.getEmail().isBlank()) {
            errors.add("Email не может иметь пустое значение");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

    }
}
