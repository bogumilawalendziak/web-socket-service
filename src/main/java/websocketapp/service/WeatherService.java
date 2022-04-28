package websocketapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import websocketapp.client.WeatherClient;
import websocketapp.model.Weather;
import websocketapp.repository.WeatherRepository;

@Service
@AllArgsConstructor
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;
    public void checkWeather() {
        Weather weather = weatherClient.getAllData();
        weatherRepository.save(weather);
    }
}
