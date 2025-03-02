//package integration.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ru.shaxowskiy.exception.LocationNotFoundException;
//import ru.shaxowskiy.models.Location;
//import ru.shaxowskiy.models.User;
//import ru.shaxowskiy.repositories.LocationRepository;
//import ru.shaxowskiy.services.LocationService;
//
//import java.math.BigDecimal;
//
//import static junit.framework.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class LocationServiceTest {
//
//    @InjectMocks
//    private LocationService locationService;
//    @Mock
//    private LocationRepository locationRepository;
//
//    private Location location;
//    @BeforeEach
//    void setUp(){
//        location = new Location();
//        location.setId(1L);
//        location.setName("Moscow");
//        location.setLatitude(BigDecimal.valueOf(55.752200));
//        location.setLongitude(BigDecimal.valueOf(37.615600));
//        User user = new User();
//        location.setUser(user);
//    }
//    @Test
//    void findById_checkFoundUser(){
//        long locationId = 1L;
//        when(locationRepository.findById(locationId)).thenReturn(location);
//        Location result = locationService.findById(locationId);
//        Assertions.assertEquals(location, result, "Возвращенный объект Location не соответствует ожидаемому");
//
//        verify(locationRepository).findById(locationId);
//    }
//
//    @Test
//    void findById_ShouldThrowLocationNotFoundException_WhenLocationNotFound() {
//        Long locationId = 1L;
//        when(locationRepository.findById(locationId)).thenReturn(null);
//        LocationNotFoundException exception = assertThrows(LocationNotFoundException.class, () -> {
//            locationService.findById(locationId);
//        });
//        assertEquals("This location is not found with id " + locationId, exception.getMessage());
//        verify(locationRepository).findById(locationId);
//    }
//}
