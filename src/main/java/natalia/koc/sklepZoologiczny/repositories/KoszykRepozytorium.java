package natalia.koc.sklepZoologiczny.repositories;

import natalia.koc.sklepZoologiczny.domain.Koszyk;
import natalia.koc.sklepZoologiczny.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KoszykRepozytorium extends JpaRepository<Koszyk, Integer> {
    Koszyk findAllByUser(User user);
    Koszyk findByUserAndNazwa(User user, String nazwa);
}
