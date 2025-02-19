package ru.shaxowskiy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaxowskiy.models.Location;
import ru.shaxowskiy.models.User;
import ru.shaxowskiy.models.dto.LocationDTO;
import ru.shaxowskiy.repositories.LocationRepository;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public void save(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        location.setLatitude(locationDTO.getCoord().getLat());
        location.setLongitude(locationDTO.getCoord().getLon());
//        location.setUser(new User());
        locationRepository.save(location);
    }
}
