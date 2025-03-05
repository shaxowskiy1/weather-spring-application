package ru.shaxowskiy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaxowskiy.exception.LocationNotFoundException;
import ru.shaxowskiy.models.Location;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.models.dto.LocationResponseDTO;
import ru.shaxowskiy.repositories.LocationRepository;
import ru.shaxowskiy.repositories.LocationRepositoryImpl;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public Location findById(Long id){
        return Optional.ofNullable(locationRepository.findById(id)).orElseThrow(() -> new LocationNotFoundException("This location is not found with id " + id));
    }

    public void save(LocationResponseDTO locationResponseDTO, User user) {
        Location location = new Location();
        location.setName(locationResponseDTO.getName());
        location.setLatitude(locationResponseDTO.getLat());
        location.setLongitude(locationResponseDTO.getLon());
        location.setUser(user);
        locationRepository.save(location);
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public List<Location> findByUser(User user) {
        return locationRepository.findByUser(user);
    }
    public void delete(BigDecimal lat, BigDecimal lon){
        locationRepository.delete(lat, lon);
    }
}
