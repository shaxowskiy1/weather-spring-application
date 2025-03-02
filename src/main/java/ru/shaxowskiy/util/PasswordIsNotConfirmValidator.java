package ru.shaxowskiy.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shaxowskiy.models.dto.UserDTO;

@Component
public class PasswordIsNotConfirmValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "", "The passwords don't match");
        }
    }
}
