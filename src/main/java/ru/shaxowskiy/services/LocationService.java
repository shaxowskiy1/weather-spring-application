package ru.shaxowskiy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaxowskiy.exception.LocationNotFoundException;
import ru.shaxowskiy.models.Location;
import ru.shaxowskiy.models.dto.WeatherResponseDTO;
import ru.shaxowskiy.repositories.LocationRepository;

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

    public void save(WeatherResponseDTO weatherResponseDTO) {
        Location location = new Location();
        location.setName(weatherResponseDTO.getName());
        location.setLatitude(weatherResponseDTO.getCoord().getLat());
        location.setLongitude(weatherResponseDTO.getCoord().getLon());
//        location.setUser(new User());
        locationRepository.save(location);
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
