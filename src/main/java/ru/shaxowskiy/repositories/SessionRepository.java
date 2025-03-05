package ru.shaxowskiy.repositories;

import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.Session;
import ru.shaxowskiy.models.User;

import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<Session, String> {
    List<Session> findByUser(User user);
}
