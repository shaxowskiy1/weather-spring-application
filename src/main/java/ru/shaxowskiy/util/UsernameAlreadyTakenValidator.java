package ru.shaxowskiy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shaxowskiy.models.dto.UserDTO;
import ru.shaxowskiy.repositories.UserRepository;

@Component
public class UsernameAlreadyTakenValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UsernameAlreadyTakenValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;
        if(userRepository.findByUsername(userDTO.getUsername()) != null){
            errors.rejectValue("username", "", "This username is already taken");
        }
    }
}
