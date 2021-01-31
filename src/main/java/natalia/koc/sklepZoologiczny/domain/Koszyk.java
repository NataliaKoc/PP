package natalia.koc.sklepZoologiczny.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Setter @Getter
public class Koszyk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer ilosc;
    @NotNull
    private String nazwa;
    @NotNull
    private Float cena;
    @NotNull
    @OneToOne
    private User user;

    public Koszyk(Integer ilosc, String nazwa, Float cena, User user) {
        this.ilosc = ilosc;
        this.nazwa = nazwa;
        this.cena = cena;
        this.user = user;
    }

    public Koszyk() { }
}
