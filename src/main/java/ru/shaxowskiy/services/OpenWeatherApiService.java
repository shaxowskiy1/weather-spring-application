package ru.shaxowskiy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shaxowskiy.models.dto.LocationDTO;

@Service
public class OpenWeatherApiService {

    @Value("${API_KEY}")
    private String API_KEY;
    private final String SEARCH_LOC_OF_NAME = "http://api.openweathermap.org/geo/1.0/direct?";
    private final String SEARCH_LOC_OF_COORD = "https://openweathermap.org/current#one";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OpenWeatherApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public LocationDTO getInfoByCity(String city) throws JsonProcessingException {
        String url = SEARCH_LOC_OF_NAME + "q=" + city + "&APPID=" + API_KEY + "&limit=" + 10;
        String forObject = restTemplate.getForObject(url, String.class);
        LocationDTO locationDTO = objectMapper.readValue(forObject, LocationDTO.class);
        return locationDTO;
    }
}
