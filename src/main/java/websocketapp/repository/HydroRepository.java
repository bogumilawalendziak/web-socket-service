package websocketapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import websocketapp.model.Hydro;

@Repository
public interface HydroRepository extends JpaRepository<Hydro,Integer> {
}
