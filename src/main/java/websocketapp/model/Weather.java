package websocketapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weatherSeq")
    @SequenceGenerator(name = "weatherSeq", sequenceName = "weatherSeq", allocationSize = 1)
    private String idStacji;
    private String stacja;
    private String dataPomiaru;
    private String godzinaPomiaru;
    private String temperatura;
    private String predkoscWiatru;
    private String kierunekWiatru;
    private String wilgotnoscWzgledna;
    private String sumaOpadu;
    private String cisnienie;
}
