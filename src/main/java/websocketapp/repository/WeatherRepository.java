package websocketapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import websocketapp.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Integer> {
}
