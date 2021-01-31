package natalia.koc.sklepZoologiczny.controllers;

import natalia.koc.sklepZoologiczny.domain.Profil;
import natalia.koc.sklepZoologiczny.domain.User;
import natalia.koc.sklepZoologiczny.repositories.ProfilRepozytorium;
import natalia.koc.sklepZoologiczny.repositories.UserRepository;
import natalia.koc.sklepZoologiczny.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProfilRepozytorium profilRepozytorium;
    private Profil profil = new Profil();


    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userCommand", new Profil());
        return "user/profilForm";
    }

    @GetMapping("/profil")
    public String profil(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("profil", profilRepozytorium.findByUser(userRepository.findByUsername(name)));
        model.addAttribute("user", userRepository.findByUsername(name));
        return "user/profil";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userCommand")
                                           User userForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/registrationForm";
        }
        userService.saveUser(userForm);
        profil.setUser(userForm);
        profilRepozytorium.save(profil);
        return "user/registrationSuccess";
    }

    @PostMapping("/registrationProfil")
    public String registrationProfil(@Valid @ModelAttribute("userCommand")
                                             Profil profil, Model model, Errors error) {
        if(error.hasErrors()) {
            return "user/profilForm";
        }

        this.profil = profil;
        model.addAttribute("userCommand", new User());
        return "user/registrationForm";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields("enabled", "roles");
    }
}