package ru.shaxowskiy.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.models.dto.UserDTO;
import ru.shaxowskiy.repositories.UserRepository;
import ru.shaxowskiy.repositories.UserRepositoryImpl;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDto){
        String password = userDto.getPassword();
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userRepository.save(user);
    }

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user;
    }
}
