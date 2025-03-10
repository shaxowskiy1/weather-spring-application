package ru.shaxowskiy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaxowskiy.models.Session;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.repositories.SessionRepository;
import ru.shaxowskiy.repositories.SessionRepositoryImpl;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void createSession(User user, HttpServletResponse res) {
        String sessionId = UUID.randomUUID().toString();
        Session session = new Session();
        session.setId(sessionId);
        session.setUser(user);
        session.setExpiresAt(LocalDateTime.now().plusHours(1));
        sessionRepository.save(session);
        Cookie cookie = new Cookie("session_id", sessionId);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 30);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public Optional<User> getUserFromSession(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("session_id".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();
                    Session session = sessionRepository.findById(sessionId);
                    if (session != null && session.getExpiresAt().isAfter(LocalDateTime.now())) {
                        return Optional.ofNullable(session.getUser());
                    }
                }
            }
        }
        return Optional.empty();
    }

    public void delete(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("session_id".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();
                    sessionRepository.delete(sessionId);

                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    res.addCookie(cookie);
                }
            }
        }

    }

    public boolean isValid(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("session_id".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();
                    if (sessionRepository.findById(sessionId) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}