package exercise.service;

import exercise.HttpClient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    ObjectMapper mapper;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeatherByCityName(String name) {
        String fullWeather = client.get("http://weather/api/v2/cities/{" + name + "}");
        JSONObject obj = new JSONObject(fullWeather);
        String temperature = obj.getString("temperature");
        String cloudy = obj.getString("cloudy");
        String humidity = obj.getString("humidity");
        String wind = obj.getString("wind");
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("temperature", temperature);
        map.put("cloudy", cloudy);
        map.put("humidity", humidity);
        map.put("wind", wind);
        return map;
    }
    // END
}
