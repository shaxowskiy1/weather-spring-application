package ru.shaxowskiy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaxowskiy.models.Session;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.repositories.SessionRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void createSession(User user, HttpServletResponse res){
        String sessionId = UUID.randomUUID().toString();
        Session session = new Session();
        session.setId(sessionId);
        session.setUser(user);
        session.setExpiresAt(LocalDateTime.now().plusHours(1));
        sessionRepository.save(session);

        Cookie cookie = new Cookie("session_id", sessionId);
        cookie.setHttpOnly(true);
        res.addCookie(cookie);
    }
}
