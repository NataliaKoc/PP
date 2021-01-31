package natalia.koc.sklepZoologiczny.controllers;

import natalia.koc.sklepZoologiczny.domain.Koszyk;
import natalia.koc.sklepZoologiczny.domain.Produkt;
import natalia.koc.sklepZoologiczny.domain.User;
import natalia.koc.sklepZoologiczny.repositories.KoszykRepozytorium;
import natalia.koc.sklepZoologiczny.repositories.ProduktRepozytorium;
import natalia.koc.sklepZoologiczny.repositories.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koszyk")
public class koszykController {
    private ProduktRepozytorium produktRepozytorium;
    private KoszykRepozytorium koszykRepozytorium;
    private UserRepository userRepository;

    public koszykController(ProduktRepozytorium produktRepozytorium,
                            KoszykRepozytorium koszykRepozytorium,
                            UserRepository userRepository) {
        this.produktRepozytorium = produktRepozytorium;
        this.koszykRepozytorium = koszykRepozytorium;
        this.userRepository = userRepository;
    }

    @GetMapping("/dodajDoKoszyka/{id}/{page}/{size}")
    public String dodajDoKoszyka(@PathVariable Integer id, @PathVariable String page, @PathVariable String size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Produkt produkt = produktRepozytorium.findById(id).get();
        User user = userRepository.findByUsername(name);
        Koszyk koszyk = koszykRepozytorium.findByUserAndNazwa(user, produkt.getNazwa());
        if(koszyk == null) {
            koszyk = new Koszyk(1, produkt.getNazwa(), produkt.getCena(), user);
        } else {
            koszyk.setIlosc(koszyk.getIlosc() + 1);
            koszyk.setUser(user);
        }
        koszykRepozytorium.save(koszyk);
        return "redirect:/produkty/showList?page="+page+"&size="+size;
    }

    @GetMapping("/koszyk")
    public String koszyk(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("koszyk", koszykRepozytorium.findAllByUser(userRepository.findByUsername(name)));
        return "zamowienia/koszyk";
    }
}
