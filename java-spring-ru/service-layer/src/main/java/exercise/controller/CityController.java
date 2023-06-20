package exercise.controller;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.IterableUtils.toList;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    private final ObjectMapper mapper;

    // BEGIN
    @GetMapping(value = "/cities/{id}")
    public Map<String, String> getWeatherByCity(@PathVariable Long id) {
        City c = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found"));
        return weatherService.getWeatherByCityName(c.getName());
    }

    @GetMapping(value = "/search")
    public ResponseEntity<String> search(@RequestParam(required = false) String name) throws JsonProcessingException {
        List<City> c;
        if (name != null) {
            c = cityRepository.findAllByNameStartingWithIgnoreCase(name);
        } else {
            c = cityRepository.findAllByOrderByNameAsc();
        }
        List<Map<String, String>> map = new ArrayList<>();
        for (City city : c) {
            Map<String, String> w = weatherService.getWeatherByCityName(city.getName());
            List.of("cloudy", "humidity", "wind").forEach(w.entrySet()::remove);
            map.add(w);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(map));
    }
    // END
}

