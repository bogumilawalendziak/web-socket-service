package websocketapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stations")
@Getter
@Setter
@NoArgsConstructor
public class Stations {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stationSeq")
    @SequenceGenerator(name = "stationSeq", sequenceName = "stationSeq", allocationSize = 1)
    private Integer id;
    private String stationId;
    private String name;
    private String województwo;
    @JsonIgnoreProperties("stations")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stations")
    private Set<Hydro> hydro;
}
