package websocketapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import websocketapp.model.Stations;
import websocketapp.model.Weather;

import java.util.Optional;

@Repository
public interface StationsRepository extends JpaRepository<Stations,Integer> {
    Optional<Stations> findByStationId(String stationId);
}
