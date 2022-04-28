package websocketapp.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import websocketapp.service.MusicService;
import websocketapp.service.WeatherService;

@Service
@AllArgsConstructor
public class ScheduleUtil {
    private final WeatherService weatherService;
    private final MusicService musicService;

    @Scheduled(fixedDelay = 12000)
    public void getWeather() {
        weatherService.checkWeather();
    }

    @Scheduled(fixedRate = 2)
    public void getMusic() {
        musicService.checkMusic();
    }
}
