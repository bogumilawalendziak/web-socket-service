package websocketapp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import websocketapp.model.Weather;

@Component
@FeignClient(name = "client", url = "http://danepubliczne.imgw.pl/api/data/hydro/")
public interface WeatherClient {
    @GetMapping
    Weather getAllData();
}