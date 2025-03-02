package ru.shaxowskiy.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.repositories.UserRepository;

@Component
public class UserNotFoundValidation implements Validator {
    private UserRepository userRepository;

    @Autowired
    public UserNotFoundValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null) {
            errors.rejectValue("username", "", "User with this username was not found");
        } else if (!BCrypt.checkpw(user.getPassword(), foundUser.getPassword())) {
            errors.rejectValue("password", "", "Not correct password");
        }
    }
}
