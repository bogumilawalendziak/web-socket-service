package websocketapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather {

        private String id_stacji;
        private String stacja;
        private String rzeka;
        private String województwo;
        private String stan_wody;
        private String stan_wody_data_pomiaru;
        private String temperatura_wody;
        private String temperatura_wody_data_pomiaru;
        private String zjawisko_lodowe;
        private String zjawisko_lodowe_data_pomiaru;
        private String zjawisko_zarastania;
        private String zjawisko_zarastania_data_pomiaru;
}
