package websocketapp.service;

import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocketapp.client.WeatherClient;
import websocketapp.model.Hydro;
import websocketapp.model.Stations;
import websocketapp.model.Weather;
import websocketapp.repository.HydroRepository;
import websocketapp.repository.StationsRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final WeatherClient weatherClient;
    private final HydroRepository hydroRepository;
    private final StationsRepository stationsRepository;


    public void checkWeather() {
        List<Weather> weatherList = weatherClient.getAllData();
        weatherList.stream().forEach(d -> {
            Stations stations;
            String stationId = d.getId_stacji();
            Optional<Stations> optionalStations = stationsRepository.findByStationId(stationId);
            if (optionalStations.isPresent()) stations = optionalStations.get();
            else {
                stations = new Stations();
                stations.setStationId(stationId);
                stations.setName(d.getStacja());
                stationsRepository.save(stations);
            }


            Hydro hydro = new Hydro();
            hydro.setRzeka(d.getRzeka());
            hydro.setStations(stations);
            hydro.setStan_wody(d.getStan_wody());
            hydro.setTemperatura_wody(d.getTemperatura_wody());
            hydro.setStan_wody_data_pomiaru(d.getStan_wody_data_pomiaru());
            hydro.setZjawisko_lodowe(d.getZjawisko_lodowe());
            hydro.setTemperatura_wody_data_pomiaru(d.getTemperatura_wody_data_pomiaru());
            hydro.setZjawisko_lodowe_data_pomiaru(d.getZjawisko_lodowe_data_pomiaru());
            hydro.setZjawisko_zarastania(d.getZjawisko_zarastania());
            hydro.setZjawisko_zarastania_data_pomiaru(d.getZjawisko_zarastania_data_pomiaru());

            hydroRepository.save(hydro);
        });

    }

}
