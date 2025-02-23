package ru.shaxowskiy.models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, max = 30, message = "Login should be between 3 and 30 characters")
    @Column(name = "login")
    private String username;
    @Column(name = "password")
    @NotEmpty(message = "Password should be not empty")
    @Size(min = 3, message = "Your password is over short")
    private String password;
}
