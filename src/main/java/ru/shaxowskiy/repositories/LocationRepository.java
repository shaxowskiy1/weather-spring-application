package ru.shaxowskiy.repositories;

import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.Location;
import ru.shaxowskiy.models.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findByUser(User user);
    void delete(BigDecimal lat, BigDecimal lon);
}
