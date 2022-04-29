package websocketapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "hydro")
@Getter
@Setter
@NoArgsConstructor
public class Hydro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hydroSeq")
    @SequenceGenerator(name = "hydroSeq", sequenceName = "hydroSeq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JsonIgnoreProperties("hydro")
    @JoinColumn(name = "stations_id", referencedColumnName = "id")
    private Stations stations;
    private String rzeka;
    private String stan_wody;
    private String stan_wody_data_pomiaru;
    private String temperatura_wody;
    private String temperatura_wody_data_pomiaru;
    private String zjawisko_lodowe;
    private String zjawisko_lodowe_data_pomiaru;
    private String zjawisko_zarastania;
    private String zjawisko_zarastania_data_pomiaru;
}
