package natalia.koc.sklepZoologiczny.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter @Getter
public class Historia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer nrZamowienia;
    @NotNull
    private LocalDate dataZamowienia;
    @NotNull
    private Float cena;
    @NotNull
    private LocalDate przewidywanyCzasDostawy;
    @ManyToOne
    private User user;

    public Historia(Integer nrZamowienia, LocalDate dataZamowienia, Float cena, LocalDate przewidywanyCzasDostawy, User user) {
        this.nrZamowienia = nrZamowienia;
        this.dataZamowienia = dataZamowienia;
        this.cena = cena;
        this.przewidywanyCzasDostawy = przewidywanyCzasDostawy;
        this.user = user;
    }

    public Historia() {

    }
}
