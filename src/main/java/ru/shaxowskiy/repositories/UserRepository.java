package ru.shaxowskiy.repositories;

import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
