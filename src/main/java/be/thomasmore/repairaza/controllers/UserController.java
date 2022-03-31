package be.thomasmore.repairaza.controllers;


import be.thomasmore.repairaza.model.Liefhebber;
import be.thomasmore.repairaza.model.User;
import be.thomasmore.repairaza.repositories.LiefhebberRepository;
import be.thomasmore.repairaza.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    LiefhebberRepository liefhebberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String register(Model model, Principal principal) {
        if (principal!=null) return "redirect:/itemlist";
        return "user/register";
    }
    @PostMapping("/register")
    public String registerPost(Model model,
                               Principal principal,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String name) {
        if (principal!=null) return "redirect:/itemlist";
        if (username==null || username.isBlank()) return "redirect:/itemlist";
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) return "redirect:/itemlist";

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setRole("USER");
        String encode = passwordEncoder.encode(password);
        newUser.setPassword(encode);
        User newSavedUser = userRepository.save(newUser);

        Liefhebber newLiefhebber = new Liefhebber();
        newLiefhebber.setNickName(name);
        newLiefhebber.setUser(newSavedUser);
        liefhebberRepository.save(newLiefhebber);

        return "redirect:/itemlist";
    }
}