package ru.shaxowskiy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shaxowskiy.models.Location;
import ru.shaxowskiy.models.dto.LocationResponseDTO;
import ru.shaxowskiy.models.dto.WeatherResponseDTO;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenWeatherApiService {

    @Value("${API_KEY}")
    private String API_KEY;
    private final String SEARCH_LOC_OF_NAME = "https://api.openweathermap.org/geo/1.0/direct?";
    private final String SEARCH_LOC_OF_COORD = "https://api.openweathermap.org/data/2.5/weather?";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OpenWeatherApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<LocationResponseDTO> getInfoAboutCityForName(String city) throws JsonProcessingException {
        String url = SEARCH_LOC_OF_NAME + "q=" + city + "&appid=" + API_KEY + "&limit=" + 10;
        String response = restTemplate.getForObject(url, String.class);
        return objectMapper.readValue(response, new TypeReference<>() {
        });
    }

    public List<WeatherResponseDTO> getInfoAboutCityForCoord(List<Location> userLocations) throws JsonProcessingException {
        List<WeatherResponseDTO> listOfCitiesWithWeather = new ArrayList<>();
        for (Location location : userLocations) {
            String url = SEARCH_LOC_OF_COORD + "&lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&APPID=" + API_KEY;
            String response = restTemplate.getForObject(url, String.class);
            WeatherResponseDTO weatherResponseDTO = objectMapper.readValue(response, WeatherResponseDTO.class);
            listOfCitiesWithWeather.add(weatherResponseDTO);
        }
        System.out.println("Complete getInfoByCoorg");
        return listOfCitiesWithWeather;
    }
}
