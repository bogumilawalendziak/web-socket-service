package websocketapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "music", uniqueConstraints =
@UniqueConstraint(columnNames = {"tittle", "singer"}))
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "musicSeq")
    @SequenceGenerator(name = "musicSeq", sequenceName = "musicSeq", allocationSize = 1)
    private Integer id;
    private String tittle;
    private String singer;
    private String date;
    private Status status;
}
