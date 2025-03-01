package ru.shaxowskiy.models.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 5, max = 15, message = "Login should be between 5 and 15 characters")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 5, max = 15, message = "Password should be between 5 and 15 characters")
    private String password;
//    @NotEmpty(message = "Password confirm not be empty")
//    @Size(min = 5, max = 15, message = "Password should be between 5 and 15 characters")
//    private String confirmPassword;
}
