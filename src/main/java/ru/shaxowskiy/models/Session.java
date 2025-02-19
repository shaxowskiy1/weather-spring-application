package ru.shaxowskiy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @Column(name = "session_id")
    private String id;

    @OneToOne
    private User user;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
}