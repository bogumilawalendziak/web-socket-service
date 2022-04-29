package websocketapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import websocketapp.model.Weather;

import java.util.List;

@Component
@FeignClient(name = "client", url = "https://danepubliczne.imgw.pl/api/data/hydro/")
public interface WeatherClient {
    @GetMapping
    List<Weather> getAllData();
}